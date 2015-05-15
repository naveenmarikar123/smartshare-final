package com.example.naveenmarikar.smartshare1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by naveenmarikar on 25/02/15.
 */
public class HomeFragment extends Fragment {

    private ProfilePictureView profilePictureView;
    private static final int REAUTH_ACTIVITY_CODE = 100;

    ImageView imageView1;
    RoundImage roundedImage;
    SearchView search;
    MapView mapView;
    GoogleMap map;




    private ViewGroup infoWindow;
    private TextView infoTitle;
    private TextView infoSnippet;
    private Button infoButton;
    private OnInfoWindowElemTouchListener infoButtonListener;



    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public TextView userNameView;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REAUTH_ACTIVITY_CODE) {
            uiHelper.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void makeMeRequest(final Session session) {
        // Make an API call to get user data and define a
        // new callback to handle the response.
        Request request = Request.newMeRequest(session,
                new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        // If the response is successful
                        if (session == Session.getActiveSession()) {
                            if (user != null) {
                                // Set the id for the ProfilePictureView
                                // view that in turn displays the profile picture.
                                profilePictureView.setProfileId(user.getId());
                                // Set the Textview's text to the user's name.
                                userNameView.setText(user.getName());
                            }
                        }
                        if (response.getError() != null) {
                            // Handle errors, will do so later.
                        }
                    }
                });
        request.executeAsync();
    }

    private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
        if (session != null && session.isOpened()) {
            // Get the user's data.
            makeMeRequest(session);
        }
    }

    private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(final Session session, final SessionState state, final Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        uiHelper.onSaveInstanceState(bundle);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final MapWrapperLayout mapWrapperLayout = (MapWrapperLayout) rootView.findViewById(R.id.map_relative_layout);





        profilePictureView = (ProfilePictureView) rootView.findViewById(R.id.selection_profile_pic);
        profilePictureView.setCropped(true);


        userNameView = (TextView) rootView.findViewById(R.id.selection_user_name);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),"Futura Light BT.ttf");
        userNameView.setTypeface(custom_font);


        TextView tx = (TextView) rootView.findViewById(R.id.textView2);
        tx.setTypeface(custom_font);

        RatingBar ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar2);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) rootView.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();






        mapWrapperLayout.init(map, 59 );

        // We want to reuse the info window for all the markers,
        // so let's create only one class member instance

        this.infoWindow = (ViewGroup) inflater.inflate(R.layout.infowindow, container, false);
        this.infoTitle = (TextView)infoWindow.findViewById(R.id.title);
        this.infoButton = (Button)infoWindow.findViewById(R.id.button);

        this.infoButtonListener = new OnInfoWindowElemTouchListener(infoButton,
                getResources().getDrawable(R.drawable.button_default_bg),
                getResources().getDrawable(R.drawable.button_default_bg))
        {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                // Here we can perform some action triggered after clicking the button
                Toast.makeText(getActivity(), marker.getTitle() + " is requested!", Toast.LENGTH_SHORT).show();

            }

        };
        this.infoButton.setOnTouchListener(infoButtonListener);
        map.setInfoWindowAdapter(new InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Setting up the infoWindow with current's marker info
                infoTitle.setText(marker.getTitle());
                infoSnippet.setText(marker.getSnippet());
                infoButtonListener.setMarker(marker);

                // We must call this to set the current marker and infoWindow references
                // to the MapWrapperLayout
                mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);
                return infoWindow;
            }
        });


    map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);


        MapsInitializer.initialize(this.getActivity());

        map.addMarker(new MarkerOptions().position(new LatLng(11.319976, 75.936238)).title("Hardware").icon(BitmapDescriptorFactory.fromResource(R.drawable.spanner)));
        map.addMarker(new MarkerOptions().position(new LatLng(
                11.320230, 75.936261
        )).title("Food").icon(BitmapDescriptorFactory.fromResource(R.drawable.food)));
        map.addMarker(new MarkerOptions().position(new LatLng(
                11.320522, 75.936773
        )).title("Books").icon(BitmapDescriptorFactory.fromResource(R.drawable.books)));
        map.addMarker(new MarkerOptions().position(new LatLng(
                11.321124, 75.936604
        )).title("Electronics").icon(BitmapDescriptorFactory.fromResource(R.drawable.monitor)));
        map.addMarker(new MarkerOptions().position(new LatLng(
                11.319654, 75.935778
        )).title("Household").icon(BitmapDescriptorFactory.fromResource(R.drawable.home)));



        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(11.31, 75.93), 10);
        map.animateCamera(cameraUpdate);


        /*expListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        */
        Session session = Session.getActiveSession();
        if (session != null && session.isOpened()) {
            // Get the user's data
            makeMeRequest(session);
        }
        return rootView;
    }



  
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Profile");
        listDataHeader.add("History");
        listDataHeader.add("Explore");
        listDataHeader.add("Search");
        listDataHeader.add("Inventory");

        // Adding child data
        List<String> hammer = new ArrayList<String>();
        hammer.add("Check Status");


        List<String> ladder = new ArrayList<String>();
        ladder.add("Check Status");


        List<String> machete = new ArrayList<String>();
        machete.add("Check Status");


        listDataChild.put(listDataHeader.get(0), hammer); // Header, Child data
        listDataChild.put(listDataHeader.get(1), ladder);
        listDataChild.put(listDataHeader.get(2), machete);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity2) activity).onSectionAttached(1);
    }


}


