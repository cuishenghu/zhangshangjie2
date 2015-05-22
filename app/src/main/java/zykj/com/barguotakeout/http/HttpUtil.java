package zykj.com.barguotakeout.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.net.URI;


/**
 * Created by ss on 15-4-14.
 */
public class HttpUtil {

    private HttpUtil(){

    }

    private static final String baseUrl="http://121.42.139.60/baguo/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        //使用默认的 cacheThreadPool
        client.setTimeout(15);
        client.setConnectTimeout(15);
        client.setMaxConnections(20);
        client.setResponseTimeout(20);
    }

    public static void login(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.LOGIN),params,handler);
    }


    public static void register(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.REGISTER),params,handler);
    }

    public static void indexPicUrl(AsyncHttpResponseHandler handler){
        client.post(UrlContants.getUrl(UrlContants.INDEXIMAGEURL),handler);
    }

    public static void dailyJoke(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.DAYJOKE),params,handler);
    }

    public static  void gifts(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.GIFTURL),params,handler);
    }

    public static void chooseAddress(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.ADDRESS),params,handler);
    }

    public static void getRestaurantList(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.RESTAURANT),params,handler);
    }

    public static void getActivities(AsyncHttpResponseHandler handler){
        client.post(UrlContants.getUrl(UrlContants.ACTIVITY),handler);
    }

    public static void getFoodTypes(AsyncHttpResponseHandler hanler,RequestParams params){
         client.post(UrlContants.getUrl(UrlContants.FOOdTYPE),params,hanler);
    }

    public static void getGoods(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.GOODS),params,handler);
    }

    public static void getAllOrder(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.AllORDER),params,handler);
    }

    public static void updateOrder(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.COMMIT_ORDER),params,handler);
    }
    public static void getTotalPrice(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.TOTAL_PRICE),params,handler);
    }

    public static void getRestDetail(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.Rest_Detail),params,handler);
    }

    public static void drawlottery(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.DRAWLOTTERY), params, handler);
    }

    public static void pay(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.PAY), params, handler);
    }

    public static void cancelOrder(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.CANCELORDER), params, handler);
    }

    public static void confirmOrder(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.CONFIRMORDER), params, handler);
    }

    /**
     * 获取指定城市的天气信息
     * @param code
     * @param handler
     */
    public static void weatherInfo(String code,AsyncHttpResponseHandler handler){
        client.get("http://apistore.baidu.com/microservice/cityinfo"+code,handler);
    }

    /**
     * 上传文件
     * @param handler
     * @param params
     */
    public static void uploadpic(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.UPLOAD_URL),params,handler);
    }

    /**
     * 更新用户详细信息
     */
    public static void updateUserInfo(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.UPDATE_USER),params,handler);
    }

    public static void modifyPwd(AsyncHttpResponseHandler handler,RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.MODIFY_PWD),params,handler);
    }

    public static void getOrderStatus(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.ORDERDETAIL),params,handler);
    }

    public static void getCompanyInfo(AsyncHttpResponseHandler handler) {
        client.post(UrlContants.getUrl(UrlContants.COMPANYINFO),handler);
    }

    public static void getMyAddress(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.MyAddress),params,handler);
    }

    public static void AddAddress(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.AddAddress),params,handler);
    }

    public static void getduibaurl(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.DUIBAURL),params,handler);
    }

    public static void getbaguoRankCate(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.BAGUORANKCATE),params,handler);
    }

    public static void getbaguoRank(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.BAGUOBANK),params,handler);
    }

    public static void commitbaguostar(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.COMMITBAGUOSTAR),params,handler);
    }

    public static void getcollections(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.COLLECTIONS),params,handler);
    }

    public static void getShopPhoto(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.SHOPPHOTO),params,handler);
    }


    public static void getIsShoucang(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.IsShoucang),params,handler);
    }

    public static void getlodgeComplaint(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.LodgeComplaint),params,handler);
    }

    public static void getChengPinInfo(AsyncHttpResponseHandler handler) {
        client.post(UrlContants.getUrl(UrlContants.ChengPinInfo),handler);
    }

    public static void getcommitChengPin(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.CommitChengPin),params,handler);
    }

    public static void getZhaoShangInfo(AsyncHttpResponseHandler handler) {
        client.post(UrlContants.getUrl(UrlContants.ZhaoShangInfo),handler);
    }

    public static void getcommitZhaoShang(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.CommitZhaoShang),params,handler);
    }

    public static void getcomments(AsyncHttpResponseHandler handler,RequestParams params) {
        client.post(UrlContants.getUrl(UrlContants.COMMENTS),params,handler);
    }
}
