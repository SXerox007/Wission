package com.example.sumitthakur.wission.ui.home;

import com.example.sumitthakur.wission.model.YoutubeResponse;

/**
 * Created by sumitthakur on 29/07/18.
 */

public interface HomeView {

    void setRecyclerviewData(YoutubeResponse commonData);

    void onEmptySearchFieldEnterError();
}
