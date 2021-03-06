package com.pixelthieves.elementtd;

import android.location.Location;
import android.widget.Toast;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.millennialmedia.android.*;
import com.pixelthieves.core.services.AdHandler;
import com.pixelthieves.core.services.AdService;

/**
 * Created by Tomas on 12/10/13.
 */
public class ShittyAdManager implements AdService {

    private final LocationValet locationValet;
    private final AndroidApplication context;
    private AdHandler handler;

    public ShittyAdManager(AndroidApplication context) {
        this.context = context;
        locationValet = new LocationValet(context, new LocationValet.ILocationValetListener() {
            public void onBetterLocationFound(Location userLocation) {
                MMRequest.setUserLocation(userLocation);
            }
        });
    }

    @Override
    public void onCreate() {
        MMSDK.initialize(context);

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        if (!locationValet.startAquire(true)) {
            Toast.makeText(context, "Unable to start acquiring location, do you have the permissions declared?",
                    Toast.LENGTH_LONG).show();

            // Manifest.permission.ACCESS_FINE_LOCATION
            // Manifest.permission.ACCESS_COARSE_LOCATION
        }
    }

    @Override
    public void onPause() {
        locationValet.stopAquire();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void cacheAd(AdType adType) {

    }

    @Override
    public void setAdHandler(AdHandler handler) {
         this.handler = handler;
    }

    @Override
    public void showAd(AdType adType) {
        final MMInterstitial ad = bakeInterstitial(Ad.Before);
        ad.setListener(new RequestListener.RequestListenerImpl() {

            @Override
            public void requestCompleted(MMAd mmAd) {
                ad.display(true);
            }

            @Override
            public void onSingleTap(MMAd mmAd) {
                notifyHandler();
                System.out.println("onSingleTap: "+mmAd);
            }

            private void notifyHandler() {
                if (handler != null) {
                    try {
                        // TODO FIX THIS ONE
                        handler.onAdFailed("Milenium media dummy ad handler");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void requestFailed(MMAd mmAd, MMException error) {
                notifyHandler();
                System.out.println("requestFailed: "+mmAd);
            }

            @Override
            public void MMAdOverlayClosed(MMAd mmAd) {
                notifyHandler();
                System.out.println("MMAdOverlayClosed: "+mmAd);
            }
        });
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ad.fetch();
            }
        });
    }

    private MMInterstitial bakeInterstitial(Ad ad) {
        final MMInterstitial interstitial = new MMInterstitial(context);
        //Add the MMRequest object to your MMInterstitial.
        interstitial.setMMRequest(new MMRequest());
        interstitial.setApid(ad.APID);
        return interstitial;
    }

}
