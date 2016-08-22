package com.shark.pcf.type;

/**
 * 截取图片地址型枚举。<br/>
 */
public class SubImagUrlType {

    /** 截取开始字符串 */
   public static String subImageUrlStart = "<img src=\"";
   /**截取以http开头的字符串*/
   public static String subImageUrlHttp = "http://";
    /** 截取图片地址的标记位置 */
   public static String subImageUrlFlag = "/..";
    /** 截取结束字符串 */
   public static String subImageUrlEnd = "\"";
   
   public static String subImageUrlPrefix = "img";
   /**截取视频字符串标签*/
   public static String subVideoUrlPrefix = "videoUrl";

    /**获取活动推送消息模板*/
    public static String titleMessage = "title.message";
    public static String activityJoinMessage = "activity.join.message";

    /**获取申请职位消息模板*/
    public static String jobMessageOne = "job.message.one";
    public static String jobMessageTwo = "job.message.two";
    public static String jobMessageHrEmailOne = "job.message.hr.email.one";
    public static String jobMessageHrEmailTwo = "job.message.hr.email.two";

    /**获取产品预约消息模板*/
    public static String productReserveOne = "product.reserve.one";
    public static String productReserveTwo = "product.reserve.two";

    /**获取调查问卷详情页面的请求路径 */
    public static String questionnaireUrl = "questionnaire.url";

    /**获取调查问卷详情的导出路径 */
    public static String exportPdfQuestionnaireUrl = "exportpdf.url";

    /**获取调查问卷详情的插件安装路径 */
    public static String exportPdfSoftwareUrl = "exportpdf.software.url";

   /**
    * 图片根路径
    */
   public static String subImageUrlAbsoluteImagePath = "absoluteImagePath";
   /**
    * 图片文件夹路径
    */
   public static String subImageFilePath = "imageFilePath";
   

   
}