package www.mmy.YummyMap.Service.api;

import com.google.gson.JsonObject;

public interface JsonConnectApi extends ConnectApi {

	JsonObject toJsonObject(String jsonStr);
}
