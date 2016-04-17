package com.shyms.corpormobile.net;

import com.shyms.corpormobile.modle.NInformation;
import com.shyms.corpormobile.modle.NInformationData;
import com.shyms.corpormobile.modle.NObject;
import com.shyms.corpormobile.modle.NObjectList;
import com.shyms.corpormobile.modle.NQRCode;
import com.shyms.corpormobile.modle.NQuestion;
import com.shyms.corpormobile.modle.NUser;

import okhttp3.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Weimin on 4/7/2016.
 */
public interface APIClient {

    @FormUrlEncoded
    @POST("/fryms/api/login.php")
    Observable<NObject<NUser>> login(@Field("username") String username,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("/fryms/api/register.php")
    Observable<NObject<NUser>> register(@Field("username") String username,
                                        @Field("password") String password,
                                        @Field("name") String name,
                                        @Field("mobile") String mobile,
                                        @Field("verify_code") String verify_code,
                                        @Field("login") int login);

    @FormUrlEncoded
    @POST("/fryms/api/register_B.php")
    Observable<NObject<NUser>> registerB();

    @GET("/fryms/api/change_password.php")
    Observable<NObject> changePassword(@Field("username") String username,
                                       @Field("password") String password,
                                       @Field("new_password") String new_password);

    @GET("/fryms/api/check_verify_code.php")
    Observable<NObject<String>> checkVerifyCode(@Query("mobile") String mobile,
                                                @Query("verify_code") String verify_code);

    @GET("/fryms/api/user_verify_code.php")
    Observable<NObject<Boolean>> UserVerifyCode(@Query("username") String username);

    @GET("/fryms/api/send_verify_code.php")
    Observable<NObject<Boolean>> sendVerifyCode(@Query("mobile") String mobile);

    @GET("/fryms/api/reset_password.php")
    Observable<NObject> ResetPassword(@Query("username") String username,
                                      @Query("password") String password,
                                      @Query("verify_code") String verify_code);

    @GET("/fryms/api/update_user_idcard.php")
    Observable<NObject<String>> updateUserIdcard(@Query("idcard") String idcard);

    @GET("/fryms/api/get_question_list.php")
    Observable<NObject<NQuestion>> getQuestionList(@Query("page_no") int page_no,
                                                   @Query("page_size") int page_size);

    @GET("/fryms/api/get_question_list_v2.php")
    Observable<NObjectList<NQuestion>> getQuestionListV2(@Query("index") int index,
                                                         @Query("count") int count);

    @GET("/fryms/api/get_my_qa.php")
    Observable<NObject<NQuestion>> getMyQAList(@Query("page_no") int page_no,
                                               @Query("page_size") int page_size,
                                               @Query("type") int type);


    @GET("/fryms/api/get_my_qa_v2.php")
    Observable<NObjectList<NQuestion>> getMyQAListV2(@Query("index") int index,
                                                     @Query("count") int count,
                                                     @Query("filter") int filter);

    // 获取我收藏的提问列表
    @GET("/fryms/api/get_favorite_question.php")
    Observable<NObject<NQuestion>> getFavoriteQuestion(@Query("page_no") int page_no,
                                                       @Query("page_size") int page_size,
                                                       @Query("type") int type);


    @GET("/fryms/api/get_favorite_question_v2.php")
    Observable<NObjectList<NQuestion>> getFavoriteQuestionV2(@Query("index") int index,
                                                             @Query("count") int count,
                                                             @Query("filter") int filter);

    //2.15 获取回答列表   此处特殊
    @GET("/fryms/api/get_answer_list.php")
    Observable<NObject<NQuestion>> getAnswerList(@Query("question_id") long question_id,
                                                 @Query("page_size") int page_size,
                                                 @Query("type") int type);

    @GET("/fryms/api/get_favorite_question_v2.php")
    Observable<NObjectList<NQuestion>> getAnswerListV2(@Query("question_id") long question_id,
                                                       @Query("answer_id") long answer_id,
                                                       @Query("count") int count);

    @GET("/fryms/api/get_answer_comment_list.php")
    Observable<NObjectList<NQuestion>> getAnswerCommentList(@Query("question_id") long question_id,
                                                            @Query("ac_id") long ac_id,
                                                            @Query("count") int count);

    //2.31  获取资讯列表
    @GET("/fryms/api/get_information_list.php")
    Observable<NObject<NInformation>> getInformationList(@Query("page_no") int page_no,
                                                         @Query("page_size") int page_size);


    @GET("/fryms/api/get_information_list_v2.php")
    Observable<NObjectList<NInformation>> getInformationListV2(@Query("information_id") long information_id,
                                                               @Query("count") int count);


    @GET("/fryms/api/get_information_list_v3.php")
    Observable<NObjectList<NInformation>> getInformationListV3(@Query("information_id") long information_id,
                                                               @Query("count") int count);

    //获取资讯详情
    @GET("/fryms/api/get_information.php")
    Observable<NObject<NInformation>> getInformation2(@Query("information_id") int information_id);

    //2.49 获取首页信息（9条）
    @GET("https://cs.yatesun.com/fryms/api/get_i9.php")
    Observable<NObjectList<NInformationData>> getInformation();

    @GET("https://cs.yatesun.com/fryms/api/get_information_list.php")
    Observable<NObjectList<NInformationData>> getInformationHome();

    @GET("/fryms/api/logout.php")
    Observable<NObject<String>> logout();

    //2.51 创建二维码信息
    @GET("/fryms/api/creat_QR_Code.php")
    Observable<NObject<NQRCode>> creatQRCode();

    @GET("/fryms/api/login_QR.php")
    Observable<NObject<NUser>> loginQR(@Query("username") String username,
                                       @Query("verify_code") String verify_code);

    @GET("/fryms/api/send_verify_code_E.php")
    Observable<NObject<Boolean>> sendVerifyCodeE(@Query("Email") String Email,
                                                 @Query("request") String request);

    @GET("/fryms/api/register_Email.php")
    Observable<NObject<NUser>> RegisterEmail(@Query("Email") String Email,
                                             @Query("request") String verify_code);

    @GET("/fryms/api/reset_password_E.php")
    Observable<NObject<NUser>> ResetPasswordE(@Query("username") String username,
                                              @Query("password") String password,
                                              @Query("verify_code") String verify_code);

    @GET("/fryms/api/update_user_information.php")
    Observable<NObject<NUser>> updateUserInformation(@Query("idcard") String idcard,
                                                     @Query("name") String name);

    @GET("/fryms/api/login_quick.php")
    Observable<NObject<NUser>> loginQuick(@Query("mobile") String mobile,
                                          @Query("verify_code") String verify_code);

    @GET("/fryms/api/login_quick_E.php")
    Observable<NObject<NUser>> loginQuickE(@Query("Email") String Email,
                                           @Query("verify_code") String verify_code);

    @GET("/fryms/api/get_mt_sample.php")
    Observable<NObject<String>> GetMtSample(@Query("MtId") String MtId);

    @FormUrlEncoded
    @POST("/fryms/api/upload.php")
    Observable<NObject<String>> Upload(@Field("file") String file);

    @GET("/fryms/api/upload.php")
    Observable<Response> acquirePNG(@Query("size") String size,
                                    @Query("guid") String guid);


}
