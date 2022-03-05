/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aranking;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class RankingServer {

    public static String Player_Name;
    public static String Gun;
    public static String Query;
    public static int Rank;
    public static String Rank_Database = "Ranking.txt";

    public static void main(String args[]) throws IOException {
        DatagramSocket sock = null;

        try {
            sock = new DatagramSocket(1000);

            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            echo("Server socket created. Waiting for incoming data...");

            while (true) {
                sock.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());

                { // Process Ranking
                    {
                        Properties prop = new Properties();
                        InputStream input = null;
                        input = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8.name()));
                        prop.load(input);

                        Query = prop.getProperty("Query");
                        Player_Name = prop.getProperty("Player_Name");
                        Gun = prop.getProperty("Gun");
                    }
                    if (Query.equals("Rank_Up")) {

                        {
                            Properties prop = new Properties();
                            InputStream input = null;
                            input = new FileInputStream(Rank_Database);
                            prop.load(input);

                            if (prop.getProperty(Player_Name) == null) {
                                Rank = 0;

                                FileWriter writer = new FileWriter(Rank_Database, true);
                                

                                Charset utf8 = Charset.forName("UTF-8");
                                Charset def = Charset.defaultCharset();

                                String charToPrint = "\\\\u0020";

                                byte[] bytes = charToPrint.getBytes("UTF-8");
                                String message = new String(bytes, def.name());

                                PrintStream printStream = new PrintStream(System.out, true, utf8.name());
                                printStream.println(message); // should print your character

                                Player_Name = Player_Name.replaceAll(" ", message);
                                writer.write(Player_Name + "=0");
                                writer.write("\r\n");
                                writer.close();

                            } else {
                                Rank = Integer.parseInt(prop.getProperty(Player_Name)) + 1;
                                Path path = Paths.get(Rank_Database);
                                Charset charset = StandardCharsets.UTF_8;

                                String content = new String(Files.readAllBytes(path), charset);
                                content = content.replaceAll(Pattern.quote(Player_Name + "=" + prop.getProperty(Player_Name)), Player_Name + "=" + Rank);
                                Files.write(path, content.getBytes(charset));
                            }

                            deleteDuplicates(Rank_Database);

                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("IOException " + e);
        }
    }

    static void deleteDuplicates(String filename) throws IOException {
        @SuppressWarnings("resource")
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Set<String> lines = new LinkedHashSet<String>();
        String line;
        String delims = "";
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            StringTokenizer str = new StringTokenizer(line, delims);
            while (str.hasMoreElements()) {
                line = (String) str.nextElement();
                lines.add(line);
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                for (String unique : lines) {
                    writer.write(unique + "\n");
                }
                writer.close();
            }
        }
        System.out.println("Removing: " + lines);
    }

    public static void echo(String msg) {
        System.out.println(msg);
    }
}