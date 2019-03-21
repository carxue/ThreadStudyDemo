package com.push.constant;


/**
 * 常量类
 * @author Administrator
 *
 */
public interface Constant {

    /**用户缓存中的session最大超时时间
     * 单位毫秒***/
    int TOKEN_MAX_TIMEOUT = 10 * 24 * 60 * 60;
    /**
     * 用户在线心跳最大间隔时间 ,单位秒
     */
    int USER_ON_LINE_MAX_TIMEOUT = 2 * 60;
    /**
     * 查询聊天室设置限流时间
     */
    int DEVICE_ROOM_TIMEOUT = 60;

    /** userId在群里的设置 --屏蔽群消息，接受消息并提醒 */
    interface GroupMemberState {
        /** 接受消息并提醒 */
        String ACCEPT_MESSAGE_ALERT = "1";
        /** 接受消息不提醒 */
        String ACCEPT_MESSAGE = "2";
        /** 屏蔽消息--拒绝接受消息 */
        String REFUSE_MESSAGE = "3";
    }
    /** 用户操作类型 */
    interface OperatorType {
        /** 登录 */
        String LOGIN = "1";
        /** 登出 token超时 */
        String LOGOUT_BY_SYS = "0";
        /**用户点击退出***/
        String LOGOUT = "2";

    }

    /**
     * 用户状态：在线、离线、离开、忙碌.
     */
    interface UserState {
        //离线 退出
        int OFF_LINE = 0;
        //在线
        int ON_LINE = 1;
        //忙碌
        int BUSY = 2;
        //离开
        int LIVE = 3;
        //手机在线
        int MOBILE_ONLINE = 4;
        //  web在线
        int WEB_ONLINE = 5;
        //异常
        int EXCEPTION = 10;

    }
    interface UserInfoState {
        /**
         * 用户被删除
         **/
        int DELETE = -1;
        /***停用****/
        int DISABLE = 0;
        /***不能登陆****/
        int ENABLE_NOLOGIN = 1;
        /***账号正常可以登录****/
        int ENABLE_LOGIN = 2;
    }

    /**
     * @消息类型
     */
    interface AtMessageType {
        /**
         * 全体成员
         */
        String ALL_MEMBER = "1111";

        /**
         * 普通成员
         */
        String COMMON_MEMBER = "1112";
    }

    /** app的权限，是否允许第三方推送 */
    interface AppKeyPushAuthority {
        //苹果
        int ALLOW_PUSH = 1;
        //小米
        int DENY_PUSH = 0;
    }

    /** app的权限，是否接收消息服务所有的消息 */
    interface AppKeyAcceptAuthority {
        //苹果
        int ACCPET = 1;
        //小米
        int REFUSE = 0;
    }

    /** 手机厂商类型 */
    interface ManufactureType {
        //苹果
        String APPLE = "0";
        //小米
        String XIAOMI = "1";
        //苹果
        String HUAWEI = "2";
        //苹果
        String MEIZU = "3";
    }

    /** app运行状态 **/
    interface AppRunState {
        /** 后台运行 */
        int BACKGROUND = 1;
        /** 前台运行 */
        int FOREGROUND = 0;
    }

    /** mqtt消息是否持久化 */
    interface MqttRetain {
        /** 成功 */
        String YES = "1";
        /** 失败 */
        String NO = "0";
    }

    /** 聊天类型*/
    interface ChatType {
        /** 单聊 */
        int SINGLE = 1;
        /** 聊天室 */
        int CHATROOM = 2;
        /** 评论消息 */
        int COMMENT = 3;
        /** 群（讨论组） */
        int GROUP = 4;
        /** 自定义主题 */
        int CUSTOM_TOPIC = 5;
    }

    /** 消息状态*/
    interface MessageStatus {
        /** 已读 */
        int READ = 1;
        /** 未读 */
        int NOT_READ = 0;
    }

    /** 是否阅后即焚标识*/
    interface MessageFlag {
        /** 阅后即焚 */
        int DELETE_AFTER_READ = 1;
        /** 普通消息 */
        int NOT_DELETE_AFTER_READ = 0;
    }

   /** 接口返回状态*/
   interface Result {
        /** 成功 */
        int SUCCESS = 1;
        /** 失败 */
        int FAIL = 0;
    }

    /** 公司状态 */
    interface CompanyState {
        /** 可用 */
        int ENABLE = 0;
        /** 禁用 */
        int DISABLE = 1;
        /** 试用 */
        int TRIAL = 2;
    }

    /** 终端类型 */
    interface DeviceType {
        /** PC */
        int PC = 1;
        /** WEB */
        int WEB = 2;
        /** ANDROID */
        int ANDROID = 3;
        /** IOS */
        int IOS = 4;
        /** SERVER */
        int SERVER = 5;
    }

    /** 性别 */
    interface Sex {
        /** 女 */
        int WOMEN = 0;
        /** 男 */
        int MEN = 1;
        /** 未知 */
        int UNKNOW = 2;
    }

    /** 版本更新 */
    interface Version {
        /** 版本硬更新 */
        String MUST_UPDATE = "2000";
    }

    /** 会话邀请 */
    interface Session {
        /** 邀请加入会话的通知 */
        String INVITE = "2401";
        /** 被邀请者同意加入会话的通知 */
        String AGREE = "2402";
        /** 被邀请者拒绝加入会话的通知 */
        String REFUSE = "2403";
    }

    /** 终端消息类型 */
    interface TerminatorMsgType {
        /** 心跳消息 */
        String HEART_BEAT = "0001";
        /** MQTT断开重连 */
        String MQTT_CONNECT_SUCCESS = "0002";
    }

    /** 消息类型 */
    interface MessageType {
        /** 多终端同步的已读回执 */
        String TERMINAL_SYNC = "0999";
        /** 消息的回执 */
        String ACK = "1000";
        /** 文本 */
        String TEXT = "1001";
        /** 图片 */
        String IMG = "1002";
        /** 音频 */
        String AUDIO = "1003";
        /** 视频 */
        String VIDEO = "1004";
        /** 文件*/
        String FILE = "1005";
        /** 地理位置*/
        String LOCATION = "1006";
        /**图文混合消息**/
        String IMG_TEXT_FIX = "1007";
        /**AT消息**/
        String AT_MSG = "1008";
        /**多人音频视频**/
        String MUCH_AUDIO_VEDIO_MSG = "1009";
        /**点对点聊天文件消息接收的回执**/
        String FILE_RECEIVE = "1010";
        /**点对点聊天阅后即焚消息已读的回执**/
        String BURN_RECEIVE = "1011";
        /**群发助手**/
        String GROUP_ASSISTANT = "1012";
        /**点对点聊天单人音频视频**/
        String SINGLE_AUDIO_VEDIO_MSG = "1013";
        /**消息撤回**/
        String RECALL_MSG = "1014";
        /**创建投票**/
        String VOTING_CREATE = "1015";
        /**创建活动**/
        String ACTIVITY_CREATE = "1016";
        /**删除投票**/
        String VOTING_DELETE = "1017";
        /**删除活动**/
        String ACTIVITY_DELETE = "1018";
        /**自定义消息**/
        String USER_DEFINED_MSG = "1999";
    }

    /** 第三方接入消息类型 */
    interface ThirdMessageType {
        /** 自定义消息 */
        String CUSTOM = "3898";
        /** 自定义主题消息  */
        String TRANSPARENT = "3899";
    }

    /**用户状态**/
    interface UserStatus {
        /**用户离线**/
        String OFFLINE = "2100";
        /**用户在线**/
        String ONLINE = "2101";
        /**用户离开**/
        String LEAVE = "2102";
        /**用户忙碌**/
        String BUSY = "2103";
        /**手机在线**/
        String MOBILE_ONLINE = "2104";
        /**WEB在线**/
        String WEB_ONLINE = "2105";
        /**用户账号被停用**/
        String FORBIDDEN = "2109";
        /**用户信息变更**/
        String INFO_UPDATE = "2110";
        /**用户修改密码，踢出登录***/
        String RESET_PASSWORD = "2111";
        /**用户被踢出登录***/
        String KICK_OUT = "2112";
    }

    /**聊天室**/
    interface ChatRoom {
        /**创建聊天室**/
        String ADD_CHATROOM = "2201";
        /**解散聊天室**/
        String DELETE_CHATROOM = "2202";
        /**聊天室增加成员**/
        String ADD_MEMBERS = "2203";
        /**聊天室删除成员**/
        String DELETE_MEMBERS = "2204";
        /**成员退出***/
        String MEMBER_EXIT = "2205";
        /***聊天室成员信息变更**/
        String MEMBER_INFO_UPDATE = "2206";
        /***聊天室信息变更**/
        String CHATROOM_INFO_UPDATE = "2207";
    }

    /**讨论组**/
    interface Group {
        /**创建讨论组**/
        String ADD_GROUP = "2501";
        /**解散讨论组**/
        String DELETE_GROUP = "2502";
        /**讨论组增加成员**/
        String ADD_MEMBERS = "2503";
        /**讨论组删除成员**/
        String DELETE_MEMBERS = "2504";
        /**成员退出***/
        String MEMBER_EXIT = "2505";
        /***讨论组成员信息变更**/
        String MEMBER_INFO_UPDATE = "2506";
        /***讨论组信息变更**/
        String GROUP_INFO_UPDATE = "2507";
        /***群主转让**/
        String GROUP_OWNER_ID_TRANFER = "2508";
        /***管理员授权**/
        String GROUP_MANAGER_AUTH = "2509";
        /***讨论组是阅后即焚模式**/
        String GROUP_BURN = "2591";
        /***群主在阅后即焚模式下删除消息**/
        String GROUP_BURN_DELETE_MESSAGE = "2592";
        /***讨论组是普通消息模式**/
        String GROUP_COMMON = "2593";
    }

    /**讨论组公告**/
    interface GroupNotification {
        /**用户登录时获取讨论组公告列表（用户未读的）**/
        String NOTICE_LOGIN = "2580";
        /**新增讨论组公告**/
        String NOTICE_ADD = "2581";
        /**删除讨论组公告**/
        String NOTICE_DELETE = "2582";
        /**修改公告状态为已读（多终端同步**/
        String NOTICE_UPDATE = "2583";
    }

    /**用户个性化设置**/
    interface UserConfig {
        /**用户消息状态同步，包括免打扰、设置黑名单**/
        String SYS_USER_MSG_STATUS = "2601";
        /**打卡验证**/
        String SIGN_CONFIRM = "2602";
        /**打卡结果**/
        String SIGN_RESULT = "2603";
    }



    /**组织架构**/
    interface Organization {
        /**组织架构更新**/
        String UPDATE = "2301";
    }
    /***用户配置信息类型**/
    interface UserConfigType {
        /**
         * 回复消息配置信息
         ***/
        int REPLY_MSG = 1;
    }
    /** ID类型 */
    interface IDType {
        /** 个人 */
        String PERSON = "P";
        /** 讨论组 */
        String GROUP = "G";
        /** session */
        String SESSION = "S";
        /***登陆历史**/
        String LOGINHISTORY = "L";
        /***部门**/
        String DEPARTMENT = "D";
        /**app 配置**/
        String APP_CONFIG = "A";
        String COMPAY_CONFIG = "CC";
        /***角色**/
        String ROLE = "R";
        /***管理员**/
        String ADMIN = "AD";
        /**通知**/
        String NOTIFICATION = "N";
        /**通知用户**/
        String USER_NOTIFACTION = "UN";

        String CHAT_MEMBER = "CM";
        /**ID 用固定数字标识表名**/
        String CHAT_ROOM = "100";

        String CLIENT = "CL";

        String MQTT_INFO = "MI";

        String APP = "AP";

        String ChAT_HANDLE_LOG= "CH";

        String FEEDBACK = "FB";
    }

    /**主题类型**/
    interface TopicType {
        /**用户消息主题(包含心跳消息)**/
        String QUEUE_USER = "$queue/sdk_user";
        /** 消息发送主题*/
        String QUEUE_MESSAGE_SEND = "$queue/sdk_send";
        /** 消息已收回执主题*/
        String QUEUE_MESSGAE_RECEIVE = "$queue/sdk_receive";
        /** 消息已读回执主题*/
        String QUEUE_MESSGAE_READ = "$queue/sdk_read";
        /** 通知发送主题*/
        String QUEUE_NOTIFICATION_SEND = "$queue/notification_send";
        /**SERVER_SDK发送消息的主题**/
        String QUEUE_SERVER_SDK = "$queue/server_sdk";
        /**重发消息的主题**/
        String QUEUE_MESSAGE_RESEND = "$queue/sdk_resend";
        /**机器人接受消息的主题**/
        String QUEUE_ROBOT_SEND = "$queue/robot_send";
        /**机器人消息重发的主题**/
        String QUEUE_ROBOT_RESEND = "$queue/robot_resend";
        //不是共享订阅主题
        /**用户消息主题(包含心跳消息)**/
        String SDK_USER = "sdk_user";
        /** 消息发送主题*/
        String SDK_SEND = "sdk_send";
        /** 消息已收回执主题*/
        String SDK_RECEIVE = "sdk_receive";
        /** 消息已读回执主题*/
        String SDK_READ = "sdk_read";
        /** 通知发送主题*/
        String NOTIFICATION_SEND = "notification_send";
        /**SERVER_SDK发送消息的主题**/
        String SERVER_SDK = "server_sdk";
        /**重发消息的主题**/
        String SDK_RESEND = "sdk_resend";
        /**机器人接受消息的主题**/
        String ROBOT_SEND = "robot_send";
        /**机器人消息重发的主题**/
        String ROBOT_RESEND = "robot_resend";
    }

    /** 讨论组成员角色 相关 */
    interface GroupMemberType {
        int MEMBER = 0;
        int ADMIN = 1;
        int SUPER_ADMIN = 2;
    }

    /***用户消息提醒设置**/
    interface UserInfoMsgTipSetting {
        /***震动可用**/
        String VIBRATEMODE_ENABLE = "1";
        /***震动不可用**/
        String VIBRATEMODE_DISABLE = "0";
        /***声音可用**/
        String VOICEMODE_ENABLE = "1";
        /***声音不可用**/
        String VOICEMODE_DISABLE = "0";
        /***声音开关-开**/
        String SOUNDSWITCH_ENABLE = "1";
        /***声音开关-关**/
        String SOUNDSWITCH_DISABLE = "0";
    }

    /** redis 相关 */
    interface RedisInfo {
        /** dbIndex */
        interface DatabaseIndex {
            int DBINDEX_0 = 0;
            /** dbIndex */
            int DBINDEX_1 = 1;
            /** dbIndex */
            int DBINDEX_2 = 2;
            /** dbIndex */
            int DBINDEX_3 = 3;
            /** dbIndex */
            int DBINDEX_4 = 4;
            /** dbIndex */
            int DBINDEX_5 = 5;
            /** dbIndex */
            int DBINDEX_6 = 6;
            /** dbIndex */
            int DBINDEX_7 = 7;
            /** dbIndex */
            int DBINDEX_8 = 8;
            /** dbIndex */
            int DBINDEX_9 = 9;
            /** dbIndex */
            int DBINDEX_10 = 10;
            /** dbIndex */
            int DBINDEX_11 = 11;
            /** dbIndex */
            int DBINDEX_12 = 12;
            /** dbIndex */
            int DBINDEX_13 = 13;
            /** dbIndex */
            int DBINDEX_14 = 14;
            /** dbIndex */
            int DBINDEX_15 = 15;
        }

        /***查询数据缓存返回结果标志**/
        interface CacheQueryResult {
            int STOP_CACHE = -1; //禁用了缓存
            int NO_CACHE = 0; // 没有缓存数据，需要缓存
            int PART_CACHE = 1; // 只缓存了一部分数据
            int ALL_CACHE = 2; // 能从缓存中取得所有数据
        }
    }

}
