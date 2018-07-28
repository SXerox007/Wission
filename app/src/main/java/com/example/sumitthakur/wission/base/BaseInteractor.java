package com.example.sumitthakur.wission.base;

import com.example.sumitthakur.wission.network.ApiError;

/**
 * Created by sumitthakur on 29/07/18.
 */

public interface BaseInteractor {
    interface ApiListener {
        /**
         * On success.
         *
         */
        void onSuccess();

        /**
         * On failure.
         *
         * @param apiError  the api error
         * @param throwable the throwable
         */
        void onFailure(ApiError apiError, Throwable throwable);
    }
}
