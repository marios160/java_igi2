package pl.mario.mautorun;

/**
 *
 * @author Mateusz
 */
public class ObjId {

    static Integer sand[] = {10, 150, 152, 170, 171, 172, 204, 205, 206, 207, 300, 301, 400,
        401, 701, 703, 1100, 1102, 1400, 1401, 1500, 1501, 1600, 1601, 2754, 2755, 2756,
        2757, 2758, 2759, 2760, 2761, 2762, 2763, 2770, 2771, 2778, 2779, 2780, 2781, 2782,
        2783, 2784, 2785, 2816, 2817, 2818, 2819, 2820, 2821, 2822, 2823, 2824, 2825, 2826,
        2827, 2828, 2829, 2830, 2831, 2832, 2833, 2834, 2835, 2836, 2837, 2838, 2839, 2840,
        2841, 2842, 2843, 2844, 2845, 2846, 2847, 2848, 2849, 2850, 2851, 2852, 2853, 2854,
        2855, 2856, 2857, 2858, 2859, 2860, 2861, 2907, 2912, 2913, 2914, 2915, 2916, 2917,
        2918, 2919, 2920, 2921, 2922, 2923, 2924, 2925, 2926, 2927, 2928, 2929, 2930, 2931,
        2932, 2933, 2934, 2935, 2936, 2937, 2938, 2939, 2940, 2941, 2942, 2943, 2944, 2945,
        2946, 2947, 2948, 2949, 2950, 2951, 2952, 2953, 2954, 2955, 2956, 2957, 2958, 2959,
        2960, 2961, 2962, 2963, 2964, 2965, 2966, 2967, 2968, 2969, 2970, 2971, 2972, 2973,
        2974, 2975, 2976, 2978, 2979, 2980, 2981, 2982, 2983, 2984, 2985, 2986, 2987, 2988,
        2989, 2990, 2991, 2992, 2993, 2994, 2995, 2996, 2997, 2998, 2999, 3000, 3001, 3002,
        3003, 3004, 3005, 3006, 3007, 3008, 3009, 3010, 3011, 3012, 3013, 3014, 3016, 3017,
        3018, 3019, 3020, 3021, 3022, 3023, 3024, 3025, 3026, 3027, 3028, 3029, 3030, 3031,
        3032, 3033, 3034, 3035, 3036, 3037, 3038, 3039, 3040, 3041, 3042, 3043, 3044, 3045,
        3048, 3049, 3050, 3051, 3052, 3053, 3054, 3055, 3056, 3057, 3058, 3059, 3060, 3061,
        3062, 3063, 3064, 3065, 3066, 3067, 3068, 3069, 3070, 3071, 3072, 3073, 3074, 3075,
        3076, 3077, 3078, 3079, 3080, 3081, 3082, 3083, 3084, 3085, 3086, 3087, 3088, 3089,
        3090, 3091, 3092, 3093, 3094, 3095, 3096, 3097, 3098, 3099, 3100, 3101, 3102, 3103,
        3104, 3105, 3106, 3107, 3108, 3109, 3110, 3111, 3112, 3113, 3114, 3115, 3116, 3117,
        3118, 3119, 3120, 3121, 3122, 3123, 3124, 3125, 3126, 3127, 3128, 3132, 3133, 3134,
        3135, 3136, 3137, 3138, 3139, 3140, 3141, 3142, 3143, 3144, 3145, 3146, 3147, 3148,
        3149, 3150, 3151, 3152, 3153, 3154, 3155, 3156, 3157, 3158, 3159, 3160, 3161, 3162,
        3163, 3164, 3165, 3166, 3167, 3168, 3169, 3170, 3171, 3172, 3173, 3178, 3179, 3180,
        3181, 3182, 3183, 3184, 3185, 3186, 3187, 3188, 3189, 3190, 3191, 3192, 3193, 3194,
        3195, 3196, 3197, 3198, 3199, 3200, 3201, 3202, 3204, 3205, 3206, 3207, 3208, 3209,
        3210, 3211, 3212, 3213, 3214, 3215, 3216, 3217, 3218, 3219, 3220, 3221, 3222, 3223,
        3224, 3225, 3226, 3227, 3342, 3343, 3344, 3345, 3346, 3347, 3348, 3349, 3350, 3351,
        3352, 3353, 3354, 3355, 3356, 3357, 3358, 3359, 3360, 3361, 3362, 3363, 3364, 3365,
        3366, 3367, 3368, 3369, 3370, 3371, 3372, 3373, 3374, 3375, 3376, 3377, 3378, 3408,
        3441, 3442, 3443, 3444, 3445, 3446, 3447, 3448, 3449, 3450, 3451, 3452, 3453, 3454,
        3455, 3456, 3457, 3458, 3459, 3460, 3461, 3462, 3463, 3464, 3465, 3466, 3467, 3468,
        3469, 3470, 3471, 3472, 3473, 3474, 3475, 3476, 3477, 3478, 3479, 3480, 3481, 3482,
        3483, 3484, 3485, 3486, 3487, 3488, 3489, 3490, 3491, 3492, 3493, 3494, 3495, 3496,
        3497, 3498, 3499, 3500, 3501, 3502, 3503, 3504, 3505, 3506, 3507, 3508, 3509, 3510,
        3511, 3512, 3513, 3514, 3515, 3516, 3517, 3518, 3519, 3520, 3521, 3522, 3523, 3524,
        3525, 3526, 3527, 3528, 3529, 3530, 3531, 3532, 3534, 3535, 3536, 3537, 3538, 3539,
        3540, 3541, 3542, 3543, 3544, 3545, 3546, 3547, 3548, 3549, 3550, 3551, 3552, 3553,
        3554, 3555, 3556, 3557, 3558, 3559, 3560, 3561, 3562, 3563, 3564, 3565, 3566, 3567,
        3568, 3569, 3570, 3571, 3572, 3573, 3574, 3575, 3576, 3588, 3589, 3590, 3591, 3592,
        3593, 3594, 3595, 3596, 3597, 3598, 3599, 3600, 3601, 3602, 3603, 3604, 3609, 3610,
        3611, 3612, 3613, 3614, 3615, 3621, 3622, 3624, 3626, 3627, 3658, 3659, 3660, 3661,
        3662, 3663, 3664, 3665, 3666, 3667, 3668, 3669, 3670, 3671, 3672, 3673, 3674, 3675,
        3676, 3705, 3706, 3707, 3708, 3709, 3710, 3711, 3712, 3713, 3714, 3715, 3716, 3717,
        3718, 3719, 3720, 3721, 3722, 3723, 3724, 3725, 3726, 3727, 3728, 3729, 3730, 3731,
        3732, 3733, 3734, 3735, 3736, 3737, 3738, 3739, 3740, 3741, 3742, 3743, 3744, 3745,
        3746, 3747, 3748, 3749, 3750, 3751, 3752, 3753, 3754, 3755, 3756, 3757, 3758, 3759,
        3760, 3761, 3762, 3763, 3764, 3765, 3766, 3767, 3768, 3769, 3770, 3771, 3772, 3773,
        3774, 3775, 3776, 3777, 3778, 3779, 3780, 3781, 3782, 3783, 3784, 3785, 3786, 3787,
        3788, 3789, 3790, 3791, 3792, 3793, 3794, 3795, 3796, 3797, 3798, 3799, 3800, 3801,
        3802, 3803, 3804, 3805, 3806, 3807, 3808, 3809, 3810, 3811, 3812, 3813, 3814, 3815,
        3816, 3817, 3818, 3819, 3820, 3821, 3822, 3823, 3824, 3825, 3826, 3827, 3828, 3829,
        3830, 3831, 3832, 3833, 3834, 3835, 3836, 3837, 3838, 3839, 3840, 3841, 3842, 3843,
        3844, 3845, 3846, 3847, 3848, 3849, 3850, 3851, 3852, 3853, 3854, 3855, 3856, 3857,
        3858, 3859, 3860, 3861, 3862, 3863, 3864, 3865, 3866, 3867, 3868, 3869, 3870, 3871,
        3872, 3873, 3874, 3875, 3876, 3877, 3878, 3879, 3880, 3881, 3882, 3883, 3884, 3885,
        3886, 3887, 3888, 3889, 3890, 3891, 3892, 3893, 3894, 3895, 3896, 3897, 3898, 3899,
        3900, 3901, 3902, 3903, 3904, 3905, 3906, 3907, 3908, 3909, 3910, 3911, 3912, 3913,
        3914, 3915, 3916, 3917, 3918, 3919, 3920, 3921, 3922, 3923, 3924, 3925, 3926, 3927,
        3928, 3929, 3930, 3931, 3932, 3933, 3934, 3935, 3936, 3937, 3938, 3939, 3940, 3941,
        3942, 3943, 3944, 3945, 3946, 3947, 3948, 3949, 3950, 3951, 3952, 3953, 3954, 3955,
        3956, 3957, 3958, 3959, 3960, 3961, 3962, 3963, 3964, 3965, 3966, 3967, 3968, 3969,
        3970, 3971, 3972, 3973, 3974, 3975, 3976, 3977, 3978, 3979, 3980, 3981, 3982, 3983,
        3984, 3985, 3986, 3987, 3988, 3989, 3990, 3991, 3992, 3993, 3994, 3995, 3996, 3997,
        3998, 3999, 4000, 4001, 4002, 4003, 4004, 4005, 4006, 4007, 4008, 4009, 4010, 4011,
        4012, 4013, 4014, 4015, 4016, 4017, 4018, 4019, 4020, 4021, 4022, 4023, 4024, 4025,
        4026, 4027, 4028, 4029, 4030, 4031, 4032, 4033, 4034, 4035, 4036, 4037, 4038, 4039,
        4040, 4041, 4042, 4043, 4044, 4045, 4046, 4047, 4048, 4049, 4050, 4051, 4052, 4053,
        4054, 4055, 4056, 4057, 4058, 4059, 4060, 4061, 4062, 4063, 4064, 4065, 4066, 4067,
        4068, 4069, 4070, 4071, 4072, 4073, 4074, 4075, 4076, 4077, 4078, 4079, 4080, 4081,
        4082, 4083, 4084, 4085, 4086, 4087, 4088, 4089, 4090, 4091, 4092, 4093, 4094, 4095, 65535};

    static Integer red[] = {4095, 4094, 3467, 3596, 4091, 4090, 4089, 4088, 4009, 4008, 4092,
        4055, 4054, 4053, 4052, 4051, 4050, 4071, 4086, 4072, 3693, 3728, 4073, 3986, 3985, 3982,
        3981, 3980, 3979, 3978, 3977, 3976, 3959, 3975, 3958, 3727, 4060, 3499, 3726, 4006, 3725,
        3517, 3933, 4078, 4075, 3961, 4070, 4069, 3960, 4068, 4067, 4066, 3764, 3945, 4019, 4018,
        3940, 3939, 3496, 3495, 3516, 4087, 3466, 4084, 4083, 4093, 4081, 4077, 4076, 3767, 3919,
        3707, 3706, 3607, 3762, 4080, 3761, 3760, 3759, 3758, 3757, 3756, 3755, 3754, 3588, 3587,
        4082, 3586, 3585, 3584, 3583, 3582, 3581, 3580, 3579, 3578, 3577, 3576, 3575, 3574, 3573,
        3572, 3571, 3570, 3569, 3568, 3567, 3566, 3549, 3564, 3563, 3562, 3561, 3560, 3559, 3558,
        3557, 3556, 3555, 3554, 3553, 3552, 3551, 3610, 3609, 3608, 3604, 3603, 3548, 3547, 3546,
        3545, 3544, 3543, 3542, 3541, 3540, 3539, 3538, 3537, 3590, 3589, 3536, 3535, 3534, 3533,
        3532, 3531, 3530, 3529, 3528, 401, 3777, 3601, 3602, 3565, 3953, 3952, 3951, 3950, 4049,
        3724, 4074, 3593, 3595, 3515, 3514, 3513, 3512, 3511, 3510, 3509, 3508, 3507, 3506, 3505,
        3504, 3503, 3502, 3501, 3606, 3594, 3983, 4048, 3605, 3597, 800, 3550, 801, 3527, 802, 3525,
        803, 3523, 3753, 3522, 3521, 3520, 3519, 3479, 3984, 3682, 3477, 404, 3524, 3498, 3518,
        3497, 402, 3779, 403, 1201, 4047, 3472, 3469, 3468, 3471, 3470, 400, 3778, 301, 300, 1100,
        3766, 4046, 1200, 1101, 3765, 3473, 3713, 3712, 3711, 3710, 3709, 3708, 3480, 3600, 3599,
        3598, 3695, 3694, 3687, 3686, 3685, 3196, 3195, 3684, 3683, 3592, 3591, 1500, 1400, 1501,
        1401, 3208, 1600, 3949, 3948, 3947, 3946, 4065, 2877, 1907, 3083, 3082, 3085, 3084, 3011,
        3010, 1380, 1381, 2874, 2873, 2876, 2875, 2872, 2871, 2870, 2869, 2866, 2865, 2868, 2867,
        2862, 2861, 2864, 2863, 2860, 2859, 2858, 2857, 2856, 2855, 2854, 2853, 2850, 2849, 2852,
        2851, 2845, 2844, 2847, 2846, 2841, 2840, 2843, 2842, 2837, 2836, 2839, 2838, 2833, 2832,
        2835, 2834, 2829, 2828, 2831, 2830, 2826, 2825, 2848, 2827, 1908, 1910, 1909, 1900, 1906,
        1905, 1901, 3086, 3478, 4064, 3763, 3476, 4062, 4061, 3944, 3475, 4063, 3500, 3474, 4024,
        4023, 4022, 4021, 4020, 3993, 3992, 3991, 3990, 3989, 3988, 3987, 3494, 3493, 3492, 3491,
        3490, 4079, 3662, 3957, 3956, 3955, 3954, 3680, 3681, 3679, 3678, 3677, 3675, 3676, 10,
        4059, 4058, 4045, 4044, 4043, 4042, 4041, 4040, 4039, 4038, 4037, 4036, 4035, 4034, 4033,
        4032, 4031, 4030, 4029, 4028, 4027, 4026, 4025, 3810, 3809, 3808, 3807, 3806, 3805, 3804,
        3803, 3802, 3801, 3800, 3799, 3798, 3797, 3796, 3795, 3794, 3793, 3792, 3791, 3790, 3789,
        3811, 3787, 3786, 3785, 3784, 3783, 4017, 4016, 4015, 4014, 4013, 4012, 4011, 4010, 4005,
        4004, 4003, 4002, 4001, 4000, 3999, 3998, 3997, 3996, 3995, 3994, 3853, 3852, 3851, 3850,
        3849, 3848, 3847, 3846, 3845, 3844, 3843, 3842, 3841, 4057, 3914, 3913, 3912, 3911, 3910,
        3909, 3908, 3907, 3906, 3905, 3904, 3903, 3902, 3901, 3900, 3899, 3898, 3897, 3896, 3895,
        3894, 3935, 3934, 3877, 3876, 3875, 3874, 3873, 3872, 3871, 3870, 3869, 3868, 3867, 3866,
        3865, 3864, 3863, 3862, 3861, 3860, 3859, 3858, 3857, 3856, 3855, 3854, 3788, 3893, 3892,
        3891, 3890, 3889, 3888, 3887, 3886, 3885, 3884, 3883, 3882, 3881, 3880, 3879, 3878, 3840,
        3839, 3838, 3837, 3836, 3835, 3834, 3833, 3832, 3831, 3830, 3829, 3828, 4056, 3661, 3943,
        3942, 3941, 3932, 3931, 3930, 3929, 3928, 3927, 3926, 3925, 3924, 3923, 3922, 3921, 3920,
        3918, 3917, 3916, 3915, 4007, 3974, 3973, 3972, 3970, 3969, 3968, 3967, 3966, 3965, 3964,
        3963, 3962, 3938, 3937, 3936, 3827, 3826, 3825, 3824, 3823, 3822, 3821, 3820, 3819, 3818,
        3817, 3816, 3815, 3814, 3813, 3812, 3971, 4085, 3526, 3489, 3488, 3487, 3486, 3485, 3484,
        3482, 3481, 3483, 3782, 3781, 3780, 3770, 3769, 3768, 3752, 3751, 3750, 3749, 3748, 3747,
        3746, 3745, 3744, 3743, 3742, 3741, 3776, 3775, 3774, 3773, 3772, 3771, 3740, 3739, 3738, 3737, 65535};

    static Integer forest[] = {4095, 4094, 4093, 4091, 4090, 4089, 4088, 4086, 3746, 4085, 4078, 4077, 4076, 4074, 4073,
        4068, 4067, 4066, 4065, 4064, 4063, 4062, 4061, 4060, 4059, 4057, 3532, 4056, 4055, 4054,
        4053, 4052, 4051, 4050, 4049, 4048, 4047, 4046, 4045, 4044, 4016, 4015, 4014, 4013, 4012,
        3531, 4041, 4040, 4039, 4038, 4037, 4036, 4035, 4034, 4033, 4032, 4031, 4030, 4029, 4028,
        4027, 4026, 4025, 4024, 4023, 4022, 4021, 4020, 4019, 4018, 4017, 3530, 4003, 4002, 4001,
        4000, 3999, 3998, 3997, 3996, 3995, 3994, 3993, 3992, 3991, 3990, 3989, 3988, 3987, 3986,
        3985, 3984, 3935, 3934, 3933, 3932, 3529, 3983, 3982, 3981, 3980, 3979, 3978, 3977, 3976,
        3975, 3974, 3973, 3972, 3971, 3970, 3969, 3968, 3967, 3966, 3965, 3964, 3963, 3962, 3961,
        3960, 3959, 3958, 3957, 3956, 3931, 3930, 3929, 3928, 3528, 3908, 3907, 3903, 3609, 3902,
        3605, 3900, 3522, 3584, 3837, 3836, 3835, 3834, 3906, 3820, 3819, 3818, 3817, 3816, 3815,
        3814, 3813, 3812, 3947, 3946, 3945, 3944, 3943, 3625, 3484, 3582, 3483, 3580, 3482, 3579,
        3901, 3766, 4087, 3526, 4004, 4058, 3897, 3896, 3616, 3615, 3614, 3613, 3600, 3595, 3594,
        3593, 3592, 3591, 3480, 3479, 3478, 3476, 3475, 3474, 3473, 3472, 3471, 3470, 3469, 3468,
        3467, 3466, 3465, 3464, 3463, 3460, 3461, 3462, 3527, 3601, 3917, 3914, 3913, 3612, 3611,
        3565, 3563, 3562, 3561, 3581, 3916, 3925, 3924, 3560, 3559, 3558, 3553, 3552, 3551, 3550,
        3549, 3851, 3624, 3577, 3912, 3575, 3573, 3572, 3571, 3567, 3566, 3915, 3911, 3576, 4084,
        4011, 3923, 3922, 3921, 3955, 4007, 3953, 3951, 3950, 4006, 3604, 3952, 3424, 3423, 3422,
        3421, 3420, 3417, 3416, 3415, 3419, 3418, 3840, 3621, 3839, 3620, 3838, 3619, 1700, 3618,
        1701, 3603, 4010, 4009, 3905, 3904, 4008, 3927, 3910, 3909, 3671, 3899, 3898, 3670, 3523,
        3811, 3942, 3428, 4092, 3548, 3459, 3458, 3919, 3918, 3672, 3081, 3888, 3954, 3920, 1300,
        3456, 1301, 3895, 1200, 3457, 1100, 3889, 1110, 1111, 1103, 3590, 3589, 3588, 3587, 3586,
        3583, 3585, 3425, 1999, 4075, 3610, 1550, 3196, 3195, 1500, 1501, 3203, 4083, 4082, 4081,
        4080, 4079, 3627, 3626, 3890, 3599, 4043, 4042, 10, 3894, 3893, 3887, 3886, 3885, 3884,
        3883, 3882, 3881, 3880, 3879, 3878, 3877, 3876, 3875, 3874, 3873, 3872, 3871, 3870, 3869,
        3868, 3867, 3833, 3832, 3831, 3830, 3829, 3828, 3827, 3826, 3825, 3824, 3823, 3822, 3821,
        3941, 4071, 3940, 3939, 3938, 3937, 3936, 3866, 3865, 3864, 3863, 3862, 3861, 3860, 3859,
        3858, 3857, 3856, 3855, 3854, 3853, 3852, 3643, 3642, 3639, 3638, 3452, 3745, 3744, 3518,
        3517, 3516, 3515, 3514, 3513, 3512, 3511, 3510, 3509, 3508, 3507, 3506, 3505, 3504, 3503,
        3502, 3501, 3500, 3499, 3498, 3497, 3496, 3892, 3850, 4005, 3849, 3848, 3847, 3846, 3669,
        3668, 3667, 3666, 3665, 3664, 3663, 3662, 3521, 3520, 3519, 3789, 3788, 3787, 3786, 3785,
        3784, 3783, 3782, 3781, 3780, 3779, 3778, 3777, 3776, 3775, 3774, 3773, 3772, 3771, 3770,
        3769, 3768, 3767, 3802, 3801, 3800, 3799, 3798, 3797, 3796, 3795, 3794, 3793, 3792, 3791,
        3790, 3845, 3844, 3843, 3842, 3841, 3810, 3809, 3808, 3807, 3806, 3805, 3804, 3803, 3495,
        3494, 3493, 3492, 3491, 3490, 3489, 3488, 3487, 3486, 3485, 3481, 3477, 3891, 3765, 3764,
        3763, 3762, 3761, 3760, 3759, 3758, 3757, 3756, 3755, 3754, 3753, 3752, 3751, 3750, 3749,
        3748, 3747, 3743, 3742, 3741, 3740, 3739, 3738, 3737, 3736, 3735, 3734, 3733, 3732, 3731,
        3730, 3729, 3728, 3727, 3726, 3725, 3724, 3723, 3661, 3660, 3659, 3658, 3657, 3656, 3655,
        3654, 3653, 3578, 3574, 3570, 3569, 3568, 3564, 3554, 3543, 3542, 3541, 3540, 3539, 3538,
        3537, 3536, 3535, 3534, 3533, 3697, 3696, 3695, 3694, 3693, 3692, 3691, 3690, 3689, 3688,
        3687, 3686, 3685, 3684, 3683, 3682, 3681, 3680, 3679, 3678, 3677, 3676, 3675, 3674, 3673,
        3709, 3722, 3721, 3720, 3719, 3718, 3717, 3716, 3715, 3714, 3713, 3712, 3711, 3710, 3708,
        3707, 3706, 3705, 3704, 3703, 3702, 3701, 3700, 3699, 3698, 3949, 3948, 3607, 3608, 3606,
        3652, 3651, 3650, 3649, 3648, 3647, 3646, 3645, 3631, 3644, 3641, 3640, 3637, 3636, 3635,
        3634, 3633, 3632, 3630, 3628, 3623, 3622, 3617, 4072, 4070, 4069, 3926, 3544, 3598, 3597,
        3596, 3525, 3524, 3435, 3434, 3433, 3432, 3431, 3430, 3427, 3426, 3429, 65535};

    static Integer timb[] = {4095, 4094, 4011, 4091, 4090, 4089, 4088, 3880, 3879, 4043, 4010, 4009, 4008, 3933, 3757,
        3756, 3755, 3754, 3753, 3751, 3749, 3748, 3737, 3746, 3937, 3936, 3935, 3745, 3744, 3743,
        3742, 3741, 3740, 3156, 3155, 3921, 3920, 3919, 3918, 3917, 3916, 3915, 3913, 3912, 3911,
        3910, 3909, 3908, 3907, 3906, 3905, 3904, 3903, 3902, 3901, 4046, 3484, 4087, 3939, 4084,
        3152, 4093, 4083, 4057, 4056, 4086, 4033, 4032, 3159, 3860, 4071, 4080, 4081, 3637, 3007,
        4012, 3839, 3838, 3837, 3836, 3835, 3002, 3001, 3000, 2999, 2998, 2997, 2996, 2995, 2994,
        2993, 2992, 2991, 2990, 2989, 2988, 2987, 2986, 2985, 2984, 2983, 2982, 2981, 2980, 2979,
        2978, 2977, 2976, 2975, 2974, 2973, 2972, 2971, 2970, 2969, 3834, 3833, 3832, 3831, 3830,
        3829, 3828, 3827, 3826, 3825, 3824, 4014, 4076, 4075, 4074, 4073, 4072, 4070, 4069, 4068,
        4067, 4066, 4064, 4063, 4062, 4061, 4060, 3898, 4059, 3479, 3758, 3052, 3051, 3050, 3049,
        3048, 3047, 3046, 3045, 3044, 3043, 3042, 3041, 3040, 3039, 3038, 3037, 3036, 3035, 3034,
        3033, 3636, 3635, 3634, 3053, 3018, 3017, 3016, 3015, 3014, 3013, 3012, 3011, 3010, 3009,
        3008, 4055, 3160, 4054, 3720, 3719, 3718, 3717, 3716, 3715, 3714, 3713, 3712, 3032, 3031,
        3030, 3029, 3028, 3027, 3711, 3154, 3465, 3627, 3626, 3929, 3924, 3376, 3375, 3374, 3923,
        3922, 3709, 3653, 3652, 3651, 3498, 3497, 3338, 3496, 3495, 3494, 3493, 3313, 3312, 3761,
        4044, 3402, 3401, 3339, 3759, 3747, 3738, 3649, 3645, 3648, 3644, 3643, 3642, 3641, 3640,
        3639, 3638, 3633, 3632, 3631, 3630, 3600, 3599, 3598, 3597, 3596, 3595, 3594, 3593, 3592,
        3591, 3590, 3589, 3588, 3587, 3586, 3585, 3584, 3583, 3582, 3581, 3580, 3579, 3578, 3577,
        3576, 3575, 3574, 3573, 3572, 3571, 3570, 3569, 3647, 3330, 3329, 4058, 4053, 4049, 4047,
        4042, 3934, 3502, 4092, 3878, 3501, 3478, 3477, 3476, 3475, 3474, 3473, 3472, 3471, 3470,
        3469, 3468, 3467, 3466, 3932, 3930, 3500, 3408, 3407, 3406, 3492, 3491, 3490, 3489, 3488,
        3487, 3486, 3485, 3462, 3373, 3396, 3343, 3342, 3341, 3340, 3337, 3336, 3335, 3334, 3333,
        3332, 3331, 3984, 3914, 3739, 4078, 3629, 3628, 3146, 3145, 3144, 3142, 3128, 3127, 3126,
        3121, 3120, 3119, 3115, 3114, 3111, 3534, 3499, 180, 3503, 3505, 3464, 3463, 3377, 3646,
        3371, 3370, 3346, 3504, 3345, 3344, 3927, 3823, 3403, 3736, 3734, 3733, 3732, 3731, 3730,
        3729, 3728, 3727, 3726, 3725, 3724, 3723, 3722, 200, 201, 3885, 3865, 3483, 3864, 3482,
        3863, 3397, 3760, 1250, 3405, 1251, 3372, 3398, 3928, 3925, 3735, 3400, 3388, 3387, 3710,
        3399, 3390, 3389, 3983, 3088, 3087, 3085, 3084, 3394, 3819, 3818, 3816, 4085, 4082, 4052,
        3082, 3081, 3075, 3058, 3352, 3351, 1600, 3080, 1401, 1504, 1400, 1503, 3070, 3069, 3068,
        3067, 3066, 3065, 3064, 3063, 3062, 3061, 3382, 3381, 3380, 3379, 3378, 3328, 3327, 3326,
        3325, 3347, 3324, 3323, 3322, 3321, 3320, 3319, 3318, 3317, 3316, 3315, 3314, 3311, 3310,
        3309, 3308, 3151, 4077, 3822, 1700, 3821, 4048, 3138, 1802, 3931, 1803, 3888, 1800, 1801,
        3820, 1805, 3841, 3900, 3349, 3886, 3350, 3348, 3107, 3109, 3108, 3106, 3105, 3507, 3104,
        3508, 3866, 666, 3896, 4051, 3892, 3891, 3890, 3481, 3480, 3895, 3894, 3893, 4079, 1500,
        4026, 4025, 4024, 4023, 4022, 4021, 4020, 4019, 4018, 4017, 4016, 4015, 4013, 4007, 4006,
        4005, 4004, 4003, 4002, 4001, 4000, 3999, 3998, 3997, 3996, 3995, 3994, 3993, 3992, 4041,
        4040, 4039, 4038, 4037, 4036, 4035, 4034, 4031, 4030, 4029, 4028, 4027, 3539, 3538, 3537,
        3536, 3535, 3960, 3963, 3962, 3961, 3959, 3958, 3957, 3956, 3955, 3954, 3953, 3952, 3951,
        3950, 3949, 3948, 3947, 3946, 3945, 3944, 3943, 3942, 3941, 3940, 3938, 3991, 3990, 3989,
        3988, 3987, 3986, 3985, 3982, 3981, 3980, 3979, 3978, 3977, 3976, 3975, 3974, 3973, 3972,
        3971, 3970, 3969, 3968, 3967, 3966, 3965, 3964, 3395, 3393, 3392, 3391, 3386, 3385, 3384,
        3383, 3369, 3368, 3367, 3366, 3365, 3364, 3363, 3362, 3361, 3360, 3359, 3358, 3357, 3356,
        3355, 3354, 3353, 1501, 3708, 3707, 3706, 3705, 3704, 3703, 3702, 3701, 3700, 3699, 3698,
        3697, 3696, 3695, 3694, 3693, 3692, 3691, 3690, 3689, 3688, 3687, 3686, 3685, 3684, 3683,
        3682, 3681, 3680, 3926, 3794, 3793, 3792, 3791, 3790, 3789, 3788, 3787, 3786, 3785, 3784,
        3783, 3782, 3781, 3780, 3442, 3441, 3440, 3439, 3438, 3437, 3436, 3435, 3434, 3433, 3432,
        3431, 3430, 3429, 3428, 3427, 3426, 3654, 3779, 3778, 3777, 3776, 3775, 3774, 3773, 3772,
        3771, 3770, 3769, 3768, 3767, 3766, 3765, 3764, 3763, 3762, 3461, 3460, 3459, 3458, 3457,
        3456, 3455, 3454, 3453, 3452, 3451, 3450, 3449, 3448, 3447, 3446, 3445, 3444, 3443, 3679,
        3678, 3677, 3676, 3675, 3674, 3673, 3672, 3671, 3670, 3669, 3668, 3667, 3666, 3665, 3664,
        3663, 3662, 3661, 3660, 3659, 3658, 3657, 3656, 3655, 1502, 3752, 3750, 3650, 3533, 3532,
        3531, 3530, 3529, 3528, 3527, 3526, 3525, 3524, 3523, 3522, 3521, 3520, 3519, 3518, 3517,
        3516, 3515, 3425, 3424, 3423, 3422, 3421, 3420, 3419, 3418, 3417, 3416, 3415, 3414, 3413,
        3412, 3411, 3410, 3409, 3625, 3624, 3623, 3622, 3621, 3620, 3619, 3618, 3617, 3616, 3615,
        3614, 3613, 3612, 3611, 3610, 3609, 3608, 3607, 3606, 3605, 3604, 3603, 3602, 3601, 3862,
        3861, 3859, 3858, 3857, 3856, 3855, 3854, 3853, 3852, 3851, 3850, 3849, 3848, 3847, 3846,
        3845, 3844, 3843, 3842, 3840, 3568, 3567, 3566, 3565, 3564, 3563, 3562, 3561, 3560, 3559,
        3558, 3557, 3556, 3555, 3554, 3553, 3552, 3551, 3550, 3549, 3548, 3547, 3546, 3545, 3544,
        3543, 3542, 3541, 3540, 3884, 3883, 3882, 3881, 3876, 3875, 3815, 3404, 3814, 3813, 3812,
        3811, 3810, 3809, 3808, 3798, 3817, 3307, 3306, 3305, 3304, 3303, 3302, 3301, 4065, 4045,
        3887, 3506, 3889, 3873, 3877, 3871, 3870, 3869, 3874, 3872, 3868, 3867, 3807, 3806, 3805,
        3804, 3803, 3802, 3801, 3800, 3799, 3797, 3796, 3795, 3514, 3513, 3512, 3511, 3510, 10, 65535};

    static Integer china[] = {4095, 4094, 4090, 4008, 4091, 4078, 4089, 4088, 4086, 3050, 4085, 3947, 3946, 3945, 3944,
        3943, 3942, 3941, 3940, 3939, 3938, 3796, 3936, 3795, 3794, 3793, 3792, 3791, 3790, 3789,
        3788, 3787, 3786, 3785, 3784, 3783, 3782, 3999, 3859, 3778, 4066, 4013, 3904, 3989, 3900,
        3987, 3899, 3781, 4034, 4033, 4032, 3762, 3897, 3823, 3822, 3821, 3820, 3811, 3810, 3809,
        3808, 3819, 3818, 3817, 3816, 3815, 3814, 3813, 3812, 3489, 3488, 3487, 3777, 3737, 3736,
        3735, 3734, 3733, 3732, 3731, 3730, 3780, 4001, 3896, 3992, 3894, 3243, 3241, 3242, 3886,
        3236, 3237, 3238, 3239, 3240, 3261, 3260, 3259, 2724, 2723, 2722, 2721, 2720, 2719, 2718,
        2717, 2716, 2715, 2697, 2696, 2695, 2694, 2693, 2692, 3905, 3898, 3883, 3882, 3881, 3880,
        2691, 2690, 2689, 2688, 2687, 2686, 2685, 2684, 2683, 2682, 2681, 2680, 2564, 2563, 2562,
        2561, 2560, 2559, 2558, 2557, 2556, 2555, 2554, 2553, 2552, 2551, 2550, 2549, 2548, 2547,
        2546, 2545, 2544, 2543, 2542, 2541, 4047, 4031, 3892, 3681, 3680, 3679, 3492, 3490, 3378,
        4087, 3232, 4004, 4061, 4060, 3928, 3273, 3272, 3271, 3270, 3269, 3268, 3267, 3266, 3265,
        4005, 3408, 3407, 3406, 3405, 3404, 3403, 3402, 3401, 3400, 3399, 3398, 3397, 3396, 3395,
        3263, 3921, 3866, 3865, 3861, 3857, 3855, 3852, 3851, 3848, 3847, 3846, 3845, 3844, 3843,
        3842, 3841, 3840, 3839, 2540, 2948, 2947, 2946, 2945, 2944, 2943, 2942, 2941, 2940, 2939,
        2938, 2937, 2936, 2935, 2934, 2933, 2932, 2931, 2930, 2929, 2928, 2927, 2926, 2925, 2924,
        2923, 2922, 2921, 2920, 2919, 2918, 2917, 2916, 2915, 2914, 2913, 2912, 2911, 2910, 2909,
        2908, 2907, 2906, 2905, 2904, 2903, 1402, 3564, 1403, 3925, 3869, 3885, 3872, 3871, 3870,
        3858, 3856, 3442, 3854, 3853, 3849, 3766, 3558, 3557, 3556, 3555, 3907, 3554, 3553, 3552,
        3551, 3474, 3473, 3472, 3471, 3470, 3455, 3454, 3453, 3452, 3451, 3450, 3449, 3448, 3447,
        3446, 3445, 3444, 3443, 4084, 4077, 3206, 2854, 2853, 4076, 4063, 3133, 3125, 3124, 3123,
        3122, 3121, 3120, 3119, 3118, 3117, 3116, 3115, 3114, 3090, 3087, 3086, 3084, 3906, 4062,
        4049, 3113, 3112, 3111, 3110, 3109, 3108, 3107, 3106, 3105, 3104, 3103, 3102, 3101, 3100,
        3099, 3098, 3097, 3096, 3095, 3094, 3093, 3092, 3091, 3073, 3072, 3071, 3070, 3069, 4048,
        3978, 3727, 3739, 3738, 3184, 3183, 3182, 3181, 3180, 3179, 3172, 3171, 3178, 3177, 3170,
        3169, 3173, 3174, 3175, 3176, 3168, 3167, 3166, 3165, 3068, 3067, 3066, 3065, 3064, 1400,
        3563, 4011, 4059, 4058, 4057, 4056, 4055, 4054, 4053, 4052, 3967, 3983, 3982, 3205, 2852,
        2851, 4007, 4006, 3950, 3935, 3966, 3964, 3204, 2850, 2849, 3963, 3948, 3959, 3807, 3018,
        3016, 3014, 3013, 3012, 3011, 3010, 3009, 3008, 3007, 2628, 2627, 2626, 2625, 2624, 2623,
        2622, 2621, 4072, 4071, 4021, 3932, 3981, 3903, 3980, 3926, 3191, 3190, 3188, 3187, 3186,
        3185, 2902, 2901, 2900, 2899, 2898, 2897, 2896, 2895, 2894, 2891, 2890, 2888, 3977, 3901,
        3235, 2830, 2829, 4038, 3486, 3485, 3484, 3974, 4015, 4012, 3850, 3202, 2848, 2847, 3972,
        3831, 4002, 3995, 3201, 2846, 2845, 3971, 4046, 4037, 3884, 3006, 3003, 3002, 3000, 2998,
        2997, 2996, 2995, 2994, 2993, 2992, 2991, 2822, 2821, 2820, 2819, 2818, 2817, 2816, 2815,
        2814, 2813, 2812, 2811, 2810, 2809, 2808, 2807, 2806, 2805, 2804, 2803, 2802, 2801, 3970,
        4019, 2800, 2799, 2798, 2797, 2796, 2795, 2794, 3969, 4018, 2989, 2988, 2987, 4028, 4027,
        4026, 2793, 2792, 2791, 2790, 2789, 2788, 2787, 2786, 2785, 2784, 2783, 2782, 2781, 2780,
        2779, 2778, 2777, 2776, 2775, 2774, 2773, 2772, 2771, 2770, 3968, 4016, 3953, 3920, 3199,
        2842, 2841, 3985, 3919, 3984, 3994, 2565, 3961, 3993, 3197, 2838, 2837, 3990, 3958, 3722,
        3801, 3779, 4051, 3721, 4050, 3836, 3835, 3194, 2836, 2835, 3483, 3482, 3481, 4000, 3997,
        3193, 2834, 2833, 3973, 3960, 3720, 3923, 4020, 2985, 2982, 2981, 2979, 2620, 2619, 2618,
        2617, 2616, 2615, 2614, 2613, 2612, 2611, 4014, 3991, 3976, 3965, 3956, 3955, 3954, 3952,
        3951, 3806, 3893, 3864, 3863, 3862, 4030, 3805, 3804, 3192, 3860, 3937, 3803, 3802, 2969,
        2966, 2965, 2963, 2961, 2960, 2959, 2958, 2957, 2956, 2955, 2954, 2610, 2609, 2608, 2607,
        2606, 2605, 2604, 2603, 3933, 3924, 3913, 3910, 2876, 2875, 2874, 2873, 2872, 2871, 2870,
        2869, 2868, 2867, 2866, 2602, 2601, 2600, 2599, 2598, 2597, 2596, 3909, 3908, 2865, 2864,
        2863, 2862, 2861, 2860, 2859, 2858, 2857, 2856, 2855, 2595, 2594, 2593, 2592, 2591, 2590,
        2589, 3772, 4003, 3998, 3962, 3957, 3830, 3776, 3775, 3829, 3828, 3827, 3826, 3774, 3773,
        3480, 3479, 3478, 3825, 3824, 3758, 3757, 3756, 3755, 3754, 3753, 3752, 3996, 3771, 3770,
        3749, 3743, 3051, 3047, 3046, 3045, 3044, 4029, 4022, 3042, 3041, 3040, 3039, 3038, 3037,
        3036, 3035, 2756, 2755, 2754, 2753, 2752, 2751, 2750, 2749, 2748, 2747, 2746, 2745, 2744,
        3742, 3741, 3034, 3031, 3030, 3028, 3026, 3020, 4025, 4024, 4023, 2743, 2742, 2741, 2740,
        2739, 2738, 2737, 2736, 3740, 2734, 2733, 2727, 2726, 2725, 2711, 2710, 2709, 2708, 400,
        3562, 2728, 2732, 2731, 3728, 3262, 2707, 2706, 2705, 2704, 2703, 2702, 2701, 2700, 2699,
        2698, 401, 3561, 2730, 3915, 3914, 3751, 3832, 3769, 3768, 3750, 4074, 3723, 3164, 3162,
        3163, 3161, 3135, 3136, 3137, 3138, 3139, 3140, 3141, 3142, 3143, 3144, 3145, 3146, 3147,
        3148, 3149, 3150, 3151, 3152, 3153, 3154, 3155, 3156, 3157, 3158, 3159, 3160, 3132, 3131,
        3130, 3129, 3128, 3127, 3126, 3082, 3078, 3077, 3075, 1401, 3560, 4073, 3902, 3912, 3760,
        3759, 2886, 2884, 2883, 2882, 2881, 2880, 2879, 2878, 2877, 2660, 2659, 2658, 2657, 2656,
        2655, 2654, 2653, 2652, 2651, 2650, 2649, 2648, 2647, 2646, 2645, 2644, 2643, 2642, 2641,
        2640, 2639, 2638, 2637, 2636, 2635, 2634, 2633, 2632, 2631, 2630, 2629, 1600, 3477, 3476,
        3475, 3975, 3917, 3288, 3063, 3062, 3061, 3060, 3059, 3058, 3057, 3056, 3055, 3054, 3053,
        3052, 2768, 2767, 2766, 2765, 2764, 2763, 2762, 2761, 2760, 2759, 2758, 2757, 1601, 3916,
        3918, 3838, 3837, 3198, 2840, 2839, 3930, 3929, 3979, 3949, 3200, 2844, 2843, 4017, 3988,
        3986, 3927, 3729, 2675, 3931, 3891, 4010, 4009, 3234, 3233, 3231, 3230, 3227, 3226, 3225,
        3224, 3223, 3222, 3221, 3220, 3219, 3218, 3217, 3216, 3215, 3214, 3213, 3212, 3211, 3210,
        3209, 3207, 2729, 2679, 2678, 3879, 3878, 3877, 3876, 3875, 3874, 3873, 3868, 3867, 2735,
        3229, 3228, 4075, 3491, 4092, 2714, 2713, 2712, 3081, 3889, 1101, 3895, 402, 3559, 3888,
        1102, 3412, 3411, 3410, 3409, 3394, 3393, 3887, 3392, 3911, 3290, 3289, 2410, 2411, 2412,
        2413, 2414, 3196, 3195, 3286, 3285, 3800, 2677, 2676, 2674, 2673, 2672, 2671, 2670, 2669,
        2668, 2663, 2662, 2661, 2400, 2402, 2401, 2403, 3208, 4036, 4035, 3767, 3765, 3764, 3763,
        3761, 3748, 3747, 3746, 3745, 3744, 3726, 3724, 3617, 3513, 3512, 3495, 3494, 3493, 10,
        4083, 4082, 4070, 4081, 4080, 4079, 3284, 3283, 3282, 4093, 3252, 4069, 4068, 4067, 3834,
        3833, 3725, 1500, 3719, 3718, 3717, 3716, 3715, 3714, 3713, 3712, 3711, 3710, 3709, 3708,
        3707, 3706, 3705, 3704, 3703, 3702, 3701, 3700, 3699, 3694, 3698, 3697, 3696, 3695, 3693,
        3692, 3691, 3690, 3689, 3688, 3687, 3686, 3685, 3684, 3683, 3682, 3678, 3676, 3251, 2588,
        2587, 2586, 2585, 2584, 2583, 2582, 2581, 2580, 2579, 2578, 2577, 2576, 2575, 3675, 3674,
        3673, 3672, 3671, 3670, 3669, 3668, 3667, 3666, 3665, 3664, 3663, 3662, 3661, 3660, 3659,
        3658, 3657, 3656, 3655, 3654, 3653, 3652, 3651, 3650, 3649, 3648, 3647, 3646, 3645, 3644,
        3643, 3642, 3641, 3640, 3639, 3638, 3637, 3636, 3635, 3634, 3633, 3632, 3631, 3630, 3629,
        3628, 3627, 3626, 3625, 3624, 3623, 3622, 3550, 3549, 3548, 3547, 3546, 3545, 3544, 3543,
        3542, 3541, 3540, 3539, 3538, 2574, 2573, 2572, 2571, 2570, 2569, 2568, 2567, 2566, 1501,
        3616, 3799, 3798, 3797, 3677, 3621, 3620, 3619, 3618, 3615, 3614, 3613, 3612, 3611, 3610,
        3609, 3608, 3607, 3606, 3605, 3604, 3590, 3589, 3588, 3587, 3586, 3585, 3584, 3583, 3582,
        3581, 3580, 3579, 3578, 3577, 3576, 3575, 3574, 3573, 3572, 3571, 3570, 3569, 3568, 3567,
        3566, 3537, 3536, 3535, 3534, 3533, 3532, 3531, 3530, 3529, 3528, 3527, 3526, 3525, 3524,
        3523, 3522, 3521, 3520, 3519, 3518, 3517, 3516, 3515, 3514, 1502, 3511, 3510, 3509, 3508,
        3507, 3506, 3505, 3504, 3503, 3502, 3501, 3500, 3499, 3498, 3497, 3496, 3469, 3468, 3467,
        3466, 3465, 3464, 3463, 3462, 3461, 3460, 3459, 3458, 3457, 3456, 3603, 3602, 3601, 3600,
        3599, 3598, 3597, 3596, 3595, 3594, 3593, 3592, 3591, 3565, 3258, 3257, 3256, 3255, 3254,
        3440, 3439, 3438, 3437, 3436, 3435, 3434, 3433, 3432, 3431, 3430, 3429, 3428, 3427, 3426,
        3425, 3424, 3423, 3422, 3421, 3420, 3419, 3418, 3417, 3416, 3415, 3414, 3413, 3391, 3390,
        3389, 3388, 3387, 3386, 3385, 3384, 3383, 3382, 3381, 3380, 3379, 4065, 4064, 3253, 3264,
        4045, 4044, 4043, 4042, 4041, 4040, 3934, 3922, 4039, 3890, 1234, 1235, 1236, 1237, 1238,
        3441, 65535};

}