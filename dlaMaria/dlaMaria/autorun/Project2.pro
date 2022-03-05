#-------------------------------------------------
#
# Project created by QtCreator 2015-01-20T11:19:03
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Project2
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    player.cpp \
    ustawienia.cpp \
    networkcfg.cpp \
    program_data.cpp \
    _server_info.cpp \
    general.cpp \
    analiza_loga.cpp \
    test.cpp \
    strc.cpp

HEADERS  += mainwindow.h \
    player.h \
    ustawienia.h \
    networkcfg.h \
    program_data.h \
    events.h \
    wytnij.h \
    _server_info.h \
    general.h \
    analiza_loga.h \
    test.h \
    strc.h

FORMS    += mainwindow.ui
