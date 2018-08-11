package learning.mpvretrofit.com.mvp_retrofit;

import java.util.ArrayList;

import learning.mpvretrofit.com.mvp_retrofit.model.Notice;

public class MainPresenter implements MainContract.Presenter, MainContract.GetNoticeIntractor.OnFinishedListener {


    private MainContract.MainView mainView;
    private MainContract.GetNoticeIntractor getNoticeIntractor;

    public MainPresenter(MainContract.MainView mainView, MainContract.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {
        mainView = null;

    }

    @Override
    public void onRefreshButton() {

        if (mainView != null) {
            mainView.showProgress();
        }
        getNoticeIntractor.getNoticeArrayList(this);

    }

    @Override
    public void requestDataFromServer() {
        getNoticeIntractor.getNoticeArrayList(this);


    }

    @Override
    public void onFinished(ArrayList<Notice> noticeArrayList) {
        if(mainView != null){
            mainView.setDataToRecycleView(noticeArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailiure(t);
            mainView.hideProgress();
        }
    }
}
