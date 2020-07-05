package com.fractalmedia.codechallenge.atom.splash;


public class SplashPresenter implements SplashContract.Presenter, SplashContract.Model.OnFinishedListener {

    private SplashContract.View splashView;

    private SplashContract.Model splashModel;


    public SplashPresenter(SplashContract.View view){
        splashView = view;
        splashModel = new SplashModel();

    }
    @Override
    public void onSuccess(String baseUrl) {
        if (splashView != null){
            splashView.hideProgress();
            splashView.setConfiguration(baseUrl);
        }
    }



    @Override
    public void onFailure(Throwable t) {
        if (splashView != null) {
            splashView.hideProgress();
            splashView.onResponseFailure(t);
        }
    }

    @Override
    public void onDestroy() {
        splashView = null;
    }

    @Override
    public void requestConfiguration() {

        if (splashView != null) {
            splashView.showProgress();
        }
        splashModel.getConfiguration(this);
    }
}
