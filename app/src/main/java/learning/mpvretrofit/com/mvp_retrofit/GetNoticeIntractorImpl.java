package learning.mpvretrofit.com.mvp_retrofit;

import android.util.Log;

import learning.mpvretrofit.com.mvp_retrofit.model.NoticeList;
import learning.mpvretrofit.com.mvp_retrofit.my_interface.GetNoticeDataService;
import learning.mpvretrofit.com.mvp_retrofit.networking.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNoticeIntractorImpl implements MainContract.GetNoticeIntractor {


    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {
        /** Create handle for the RetrofitInstance interface*/
        GetNoticeDataService service = RetrofitInstance.getRetrofitInsance().create(GetNoticeDataService.class);

        Call<NoticeList>call= service.getNoticeData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<NoticeList>() {
            @Override
            public void onResponse(Call<NoticeList> call, Response<NoticeList> response) {
                onFinishedListener.onFinished(response.body().getNoticeArrayList());

            }

            @Override
            public void onFailure(Call<NoticeList> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }
        });

    }
}
