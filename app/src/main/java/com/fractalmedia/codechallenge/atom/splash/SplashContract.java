package com.fractalmedia.codechallenge.atom.splash;

public class SplashContract {

    public interface View {
        // Spinner
        void showProgress();
        void hideProgress();

        void setConfiguration(String baseImagesUrl);
        void onResponseFailure(Throwable throwable);
    }
    public interface Model{
        interface OnFinishedListener{
            void onSuccess (String baseUrl);
            void onFailure(Throwable t);
        }

        void getConfiguration(SplashContract.Model.OnFinishedListener onFinishedListener);
    }

    public interface Presenter{
        void onDestroy();
        void requestConfiguration(); // Pedimos configuración al api rest
    }
}
