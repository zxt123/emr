package com.zxt.emr.utils;


public class ErrCodeMap {

    private ErrCodeMap() {
    }

    public static final int NONE = 0x00;                  // Dec:  0 无错误

    // 请求阶段
    public static final int LACK_NECESSARY_PARAMS = 0x01;  // Dec:  1 缺失必要参数
    public static final int SIGN_INVALID = 0x02;           // Dec:  2 签名无效

    // 执行阶段
    public static final int ACCOUNT_TOKEN = 0x10;          // Dec: 16 账号已存在
    public static final int ACCOUNT_INVALID = 0x11;        // Dec: 17 账号不存在
    public static final int DEVICE_TOKEN = 0x12;           // Dec: 18 设备已存在
    public static final int DEVICE_INVALID = 0x13;         // Dec: 19 设备不存在
    public static final int ACCOUNT_FORMAT_ERROR = 0x14;   // Dec: 20 账号格式错误
    public static final int PASSWORD_FORMAT_ERROR= 0x15;   // Dec: 21 密码格式错误
    public static final int PASSWORD_INVALID = 0x16;       // Dec: 22 密码无效
    public static final int SEX_OUT_RANGE = 0x17;          // Dec: 23 性别取值超出范围
    public static final int BIRTHDAY_OUT_RANGE = 0x18;     // Dec: 24 生日取值超出范围
    public static final int IMG_URL_FORMAT_ERROR = 0x19;   // Dec: 25 图像连接格式错误
    public static final int PLATFORM_TOKEN = 0x1A;         // Dec: 26 第三方平台已绑定
    public static final int PLATFORM_INVALID = 0x1B;       // Dec: 27 无效的第三方平台
    public static final int PLATFORM_ERR = 0x1C;           // Dec: 28 错误的第三方消息
    public static final int SMS_INVALID = 0x1D;            // Dec: 29 错误的第三方消息
    public static final int PERMISSION_DENIED = 0x1E;      // Dec: 30 没有权限

    public static final int UNREALIZED_FUNCTION = 0x40;    // Dec: 64 未实现功能
}
