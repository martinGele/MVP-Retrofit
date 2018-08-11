package learning.mpvretrofit.com.mvp_retrofit;

import java.util.ArrayList;

import learning.mpvretrofit.com.mvp_retrofit.model.Notice;

public interface MainContract {

    /*
     * Call when user interact with the view and other when view OnDestroy()
     */

    interface Presenter{
        void onDestroy();
        void onRefreshButton();
        void requestDataFromServer();
    }
    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetNoticeInteractorImpl class
     **/

    interface MainView{

        void showProgress();
        void hideProgress();
        void setDataToRecycleView(ArrayList<Notice>noticeArrayList);
        void onResponseFailiure(Throwable t);
    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Notice> noticeArrayList);
            void onFailure(Throwable t);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }
}
