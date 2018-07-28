package com.example.sumitthakur.wission.ui.home;

/**
 * Created by sumitthakur on 29/07/18.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView view;
    private HomeInteractorImpl interactor;

    public HomePresenterImpl(HomeView homeActivity, HomeInteractorImpl homeInteractor) {
        this.view = homeActivity;
        this.interactor = homeInteractor;
    }

    @Override
    public void getVideosYoutube() {
        interactor.getYoutubeVideos();
    }
}
