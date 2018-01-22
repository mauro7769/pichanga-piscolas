package cl.duoc.pichangaspiscolas.api;

import cl.duoc.pichangaspiscolas.utils.NoConnectivityException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public abstract class ApiCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        int statusCode = response.code();
        if(response.isSuccessful()){
            onRequestSuccess(response, statusCode);
        }
        else {
            switch (statusCode) {
                case 401:
                    onUnauthorizedError(response);
                    break;
                default:
                    onRequestError(response, statusCode);
                    break;
            }
        }
        onCallFinished();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if(t instanceof NoConnectivityException)
            onInternetConnectionError();
        else
            onFailured(t);

        onCallFinished();
    }

    public abstract void onInternetConnectionError();

    public abstract void onFailured(Throwable t);

    public void onUnauthorizedError(Response<T> response){

    };

    public abstract void onRequestSuccess(Response<T> response, int statusCode);

    public abstract void onRequestError(Response<T> response, int statusCode);

    public void onCallFinished(){}
}
