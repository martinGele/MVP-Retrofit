package learning.mpvretrofit.com.mvp_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import learning.mpvretrofit.com.mvp_retrofit.adapter.NoticeAdapter;
import learning.mpvretrofit.com.mvp_retrofit.model.Notice;
import learning.mpvretrofit.com.mvp_retrofit.my_interface.RecyclerItemClickListener;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolbarAndRecyclerView();
        initProgressBar();

        presenter = new MainPresenter((MainContract.MainView) this,new GetNoticeIntractorImpl());
        presenter.requestDataFromServer();
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);

    }

    private void initializeToolbarAndRecyclerView() {


        recyclerView = findViewById(R.id.recycler_view_employee_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }
    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClickCall(Notice notice) {
            Toast.makeText(MainActivity.this,
                    "List title:  " + notice.getTitle(),
                    Toast.LENGTH_LONG).show();
        }


    };

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecycleView(ArrayList<Notice> noticeArrayList) {
        NoticeAdapter adapter = new NoticeAdapter(noticeArrayList , recyclerItemClickListener);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResponseFailiure(Throwable t) {

    }
}
