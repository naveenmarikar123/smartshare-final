package com.example.naveenmarikar.smartshare1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.smartshare.Item;
import com.smartshare.Requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mikepenz.iconics.typeface.FontAwesome.Icon;

//import com.quinny898.library.persistentsearch.SearchBox;
//import com.quinny898.library.persistentsearch.SearchResult;


/**
 * Created by naveenmarikar on 27/04/15.
 */
public class NewActivity extends AppCompatActivity {

    public ProgressDialog pdia;

    //Marker Details

    public String OwnerID;
    public String ItemID;

    public List<Item> searchResults = null;


    //private ProfilePictureView profilePictureView;
    private static final int REAUTH_ACTIVITY_CODE = 100;

    private ViewGroup infoWindow;
    private TextView infoTitle;
    private TextView infoSnippet;
    private TextView infoOwnerName;
    private TextView infoOwnerRating;
    private TextView infoItemRating;
    private TextView infoItemDescription;
    private TextView infoADistance;
    private ImageView infoIcon;


    private Button infoButton;
    private OnInfoWindowElemTouchListener infoButtonListener;

    Boolean isSearch;
    private SearchBox search;
    HashMap<String, HashMap> extraMarkerInfo = new HashMap<String, HashMap>();


    public String facebookId;

    //save our header or result
    private AccountHeader.Result headerResult = null;
    private Drawer.Result result = null;

    public TextView userNameView;
    public ProfilePictureView mProfilePictureView;
    public String name;
    GraphUser x;

    MapView mapView;
    GoogleMap map;

    public String fStatus;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SearchBox.VOICE_RECOGNITION_CODE && resultCode == this.RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches);
        }
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
                                mProfilePictureView.setProfileId(user.getId());
                                userNameView.setText(user.getName());
                                facebookId = user.getId();
                                Log.d("My ID", facebookId);
                                FetchJSON m = (FetchJSON) new FetchJSON().execute();
                            }
                        }
                        if (response.getError() != null) {
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

    @Override
    public void onResume() {
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

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Session session = Session.getActiveSession();
        if (session != null && session.isOpened()) {
            makeMeRequest(session);
        }


        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        setContentView(R.layout.nactivity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new Drawer().withActivity(this).withHeader(R.layout.header).withToolbar(toolbar).addDrawerItems(
                new PrimaryDrawerItem().withName("Home").withIcon(Icon.faw_home).withIdentifier(1).withCheckable(false),
                new PrimaryDrawerItem().withName("Inventory").withIcon(Icon.faw_briefcase).withIdentifier(2),
                new PrimaryDrawerItem().withName("Transactions").withIcon(Icon.faw_angle_double_right).withIdentifier(3),
                new SecondaryDrawerItem().withName("Settings").withIdentifier(4).withIcon(Icon.faw_cog),
                new SecondaryDrawerItem().withName("Sign Out").withIdentifier(5).withIcon(Icon.faw_eject))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(NewActivity.this, NewActivity.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(NewActivity.this, InventActivity.class);
                                startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(NewActivity.this, TransActivity.class);
                                startActivity(intent);

                            } else if (drawerItem.getIdentifier() == 5) {
                                if (Session.getActiveSession() != null) {
                                    Session.getActiveSession().closeAndClearTokenInformation();
                                }
                                Session.setActiveSession(null);
                                intent = new Intent(NewActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                }).withSavedInstance(savedInstanceState).build();
        userNameView = (TextView) findViewById(R.id.name);
        mProfilePictureView = (ProfilePictureView) findViewById(R.id.circleView);
        search = (SearchBox) findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        search.setLogoText("Click To Search");
        SearchResult option = new SearchResult("MacBook", getResources().getDrawable(R.mipmap.electronics), "1");
        search.addSearchable(option);
        option = new SearchResult("MacBook", getResources().getDrawable(R.mipmap.tools), "2");
        search.addSearchable(option);

        search.setSearchListener(new SearchBox.SearchListener() {


            private String mSearchTerm;

            @Override
            public void onSearchOpened() {

                //Use this to tint the screen
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }

            @Override
            public void onSearchTermChanged() {

            }

            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(NewActivity.this, searchTerm + " Searched", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSearchCleared() {
                search.clearResults();
            }
        });


    }

    class FetchJSON extends AsyncTask<Void, Void, List<Item>> {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        List<Item> listOfItems = null;
        String JsonStr = null;


        @Override
        protected List<Item> doInBackground(Void... params) {
            try {
                Log.d("ID before Request", "" + facebookId);
                listOfItems = Requests.getAllNearbyItems("");
                Log.d("NAVEEN", "It worked");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return listOfItems;
        }

        @Override
        protected void onPostExecute(List<Item> nearbyItems) {
            super.onPostExecute(nearbyItems);
            //doSearch(nearbyItems);
            plotMap(nearbyItems);
            //new CheckForm().execute();
            //doDSearch();
        }


    }

    private void doDSearch() {


        search.setSearchListener(new SearchBox.SearchListener() {


            private String mSearchTerm;

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }

            @Override
            public void onSearchTermChanged() {

                String searchterm;
                searchterm = search.getSearchText();
                if (searchterm.length() > 3) {
                    new DynamicSearch().execute(searchterm);
                }

            }


            @Override
            public void onSearch(String searchTerm) {
                //Toast.makeText(MainActivity.this, searchTerm + " Searched", Toast.LENGTH_LONG).show();

                Integer id = Integer.valueOf(searchTerm);

                for (int i = 0; i < searchResults.size(); i++) {

                    if (id == searchResults.get(i).getItemId())
                    {
//                        Intent intent = new Intent(NewActivity.this, ItemsActivity.class);
//                        intent.putExtra("ItemName", searchResults.get(i).getItemName());
//                        intent.putExtra("ItemDescription", searchResults.get(i).getItemDescription());
//                        intent.putExtra("ItemId", searchResults.get(i).getItemId());
//                        startActivity(intent);
                    }
                }

            }

            @Override
            public void onSearchCleared() {
                search.clearResults();
            }


        });
    }

    class CheckForm extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... params) {
            //SERVER CODE
            return null;
        }

        @Override
        protected void onPostExecute(Integer check) {
            super.onPostExecute(check);
            openFormPage(check);
        }
    }

    private void openFormPage(Integer check) {
        if (check != 0) {

            Intent intent = new Intent(this, FormActivity.class);
            intent.putExtra("FacebookID", facebookId);
            startActivity(intent);
        }
    }

    private void plotMap(List<Item> nearbyItems) {

        final MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        final MapWrapperLayout mapWrapperLayout = (MapWrapperLayout) findViewById(R.id.map_relative_layout);
        final GoogleMap map = mapFragment.getMap();

        mapWrapperLayout.init(map, getPixelsFromDp(this, 39 + 20));

        final Typeface custom_font = Typeface.createFromAsset(getAssets(),"Roboto-Medium.ttf");



        this.infoWindow = (ViewGroup) getLayoutInflater().inflate(R.layout.infowindow, null);
        this.infoTitle = (TextView) infoWindow.findViewById(R.id.title);
        this.infoButton = (Button) infoWindow.findViewById(R.id.button);
        this.infoOwnerName = (TextView) infoWindow.findViewById(R.id.ownername);
        this.infoOwnerRating = (TextView) infoWindow.findViewById(R.id.ownerrating);
        this.infoItemDescription = (TextView) infoWindow.findViewById(R.id.itemDescription);
        this.infoItemRating = (TextView) infoWindow.findViewById(R.id.itemRating);
        this.infoADistance = (TextView) infoWindow.findViewById(R.id.aDistance);
        this.infoIcon = (ImageView) infoWindow.findViewById(R.id.ticon);


        this.infoButtonListener = new OnInfoWindowElemTouchListener(infoButton,
                getResources().getDrawable(R.drawable.button_default_bg),
                getResources().getDrawable(R.drawable.button_default_bg)) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                HashMap<String, String> marker_data = extraMarkerInfo.get(marker.getId());
                OwnerID = marker_data.get("OwnerID");
                ItemID = marker_data.get("ItemID");
                Toast.makeText(getApplicationContext(), marker_data.get("OwnerName") + " is requested!", Toast.LENGTH_SHORT).show();
            }

        };
        this.infoButton.setOnTouchListener(infoButtonListener);
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Setting up the infoWindow with current's marker info

                HashMap<String, String> marker_data = extraMarkerInfo.get(marker.getId());




                infoTitle.setText(marker.getTitle().toUpperCase());
                infoButtonListener.setMarker(marker);
                infoOwnerName.setText(marker_data.get("OwnerName"));
                infoOwnerRating.setText(marker_data.get("OwnerRating"));
                infoItemDescription.setText(marker_data.get("ItemDescription"));
                infoADistance.setText(marker_data.get("ADistance"));
                infoItemRating.setText(marker_data.get("ItemRating"));

                if(marker_data.get("ItemType").equalsIgnoreCase("electronics"))
                {
                    infoIcon.setImageResource(R.mipmap.electronics);
                }
                else if(marker_data.get("ItemType").equalsIgnoreCase("tools"))
                {
                    infoIcon.setImageResource(R.mipmap.tools);
                }
                else if(marker_data.get("ItemType").equalsIgnoreCase("books"))
                {
                    infoIcon.setImageResource(R.mipmap.books);
                }

                infoIcon.setImageResource(R.mipmap.electronics);



                    infoTitle.setTypeface(custom_font);

//                infoOwnerName.setTypeface(custom_font);
//                infoItemDescription.setTypeface(custom_font);
//                infoOwnerRating.setTypeface(custom_font);
//                infoADistance.setTypeface(custom_font);
//                infoItemRating.setTypeface(custom_font);

                // We must call this to set the current marker and infoWindow references
                // to the MapWrapperLayout
                mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);
                return infoWindow;
            }
        });


        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        MapsInitializer.initialize(this);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(
                11.321235, 75.936962
        ), 17);
        map.animateCamera(cameraUpdate);

        Marker marker;
//        marker = map.addMarker(new MarkerOptions().position(new LatLng(
//                11.321235, 75.936962
//        )).title("MacBook").icon(BitmapDescriptorFactory.fromResource(R.mipmap.tools)));
//        HashMap<String, String> data = new HashMap<String, String>();
//        data.put("ItemRating",""+5);
//        data.put("OwnerRating",""+4);
//        data.put("OwnerName","Naveen Marikar");
//        data.put("ItemDescription","Good Working Condition");
//        data.put("ADistance",""+20);
//
//
//        Log.d("Check Map","DONE");
//        extraMarkerInfo.put(marker.getId(),data);


        for (Item currentItem : nearbyItems) {

            String itemType = currentItem.getItemType();
            if (itemType.equals("hardware")) {

                marker = map.addMarker(new MarkerOptions().position(new LatLng(currentItem.getLocationLatitude(),
                        currentItem.getLocationLongitude())).title(currentItem.
                        getItemDescription()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.user)));




                HashMap<String, String> data = new HashMap<String, String>();

                data.put("ItemRating", "" + currentItem.getItemRating());
                data.put("OwnerRating", "" + currentItem.getOwnerRating());
                data.put("OwnerName", currentItem.getOwnerName());
                data.put("ItemStatus", currentItem.getItemStatus());
                data.put("ItemDescription", currentItem.getItemDescription());
                data.put("OwnerID", "" + currentItem.getItemOwnerId());
                data.put("ItemID", "" + currentItem.getItemId());
                data.put("ItemType",currentItem.getItemType());

                extraMarkerInfo.put(marker.getId(), data);

            } else if (itemType.equals("electronics")) {

                marker = map.addMarker(new MarkerOptions().position(new LatLng(currentItem.getLocationLatitude(),
                        currentItem.getLocationLongitude())).title(currentItem.
                        getItemName()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.user)));

                HashMap<String, String> data = new HashMap<String, String>();

                data.put("ItemRating", "" + currentItem.getItemRating());
                data.put("OwnerRating", "" + currentItem.getOwnerRating());
                data.put("OwnerName", currentItem.getOwnerName());
                data.put("ItemStatus", currentItem.getItemStatus());
                data.put("ItemDescription", currentItem.getItemDescription());
                data.put("OwnerID", "" + currentItem.getItemOwnerId());
                data.put("ItemID", "" + currentItem.getItemId());
                data.put("ItemType",currentItem.getItemType());


                extraMarkerInfo.put(marker.getId(), data);


            } else if (itemType.equals("sports")) {
                marker = map.addMarker(new MarkerOptions().position(new LatLng(currentItem.getLocationLatitude(),
                        currentItem.getLocationLongitude())).title(currentItem.
                        getItemName()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.user)));

                HashMap<String, String> data = new HashMap<String, String>();

                data.put("ItemRating", "" + currentItem.getItemRating());
                data.put("OwnerRating", "" + currentItem.getOwnerRating());
                data.put("OwnerName", currentItem.getOwnerName());
                data.put("ItemStatus", currentItem.getItemStatus());
                data.put("ItemDescription", currentItem.getItemDescription());
                data.put("OwnerID", "" + currentItem.getItemOwnerId());
                data.put("ItemID", "" + currentItem.getItemId());
                data.put("ItemType",currentItem.getItemType());


                extraMarkerInfo.put(marker.getId(), data);


            } else if (itemType.equals("household")) {
                marker = map.addMarker(new MarkerOptions().position(new LatLng(currentItem.getLocationLatitude(),
                        currentItem.getLocationLongitude())).title(currentItem.
                        getItemName()).icon(BitmapDescriptorFactory.fromResource(R.mipmap.user)));

                HashMap<String, String> data = new HashMap<String, String>();

                data.put("ItemRating", "" + currentItem.getItemRating());
                data.put("OwnerRating", "" + currentItem.getOwnerRating());
                data.put("OwnerName", currentItem.getOwnerName());
                data.put("ItemStatus", currentItem.getItemStatus());
                data.put("ItemDescription", currentItem.getItemDescription());
                data.put("OwnerID", "" + currentItem.getItemOwnerId());
                data.put("ItemID", "" + currentItem.getItemId());
                data.put("ItemType",currentItem.getItemType());


                extraMarkerInfo.put(marker.getId(), data);


            } else if (itemType.equals("books")) {

            } else if (itemType.equals("others")) {
            }
        }
    }


//    private void doSearch(final List<Item> nearbyItems) {
//
//
//        Toast.makeText(NewActivity.this, facebookId, Toast.LENGTH_LONG).show();
//        search = (SearchBox) findViewById(R.id.searchbox);
//        search.setLogoText("Click To Search");
//        for (int i = 0; i < nearbyItems.size(); i++) {
//            SearchResult option = new SearchResult(nearbyItems.get(i).getItemDescription().toUpperCase(), getResources().getDrawable(R.mipmap.tools));
//            search.addSearchable(option);
//        }
//        search.setSearchListener(new SearchBox.SearchListener() {
//
//            @Override
//            public void onSearchOpened() {
//                //Use this to tint the screen
//            }
//
//            @Override
//            public void onSearchClosed() {
//                //Use this to un-tint the screen
//            }
//
//            @Override
//            public void onSearchTermChanged() {
//            }
//
//            @Override
//            public void onSearch(String searchTerm) {
//                //Toast.makeText(MainActivity.this, searchTerm + " Searched", Toast.LENGTH_LONG).show();
////
////                for (int i = 0; i < 3; i++) {
////                    if (searchTerm.equalsIgnoreCase(nearbyItems.get(i).getItemDescription())) {
////                        Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
////                        intent.putExtra("ItemName", nearbyItems.get(i).getItemName());
////                        intent.putExtra("ItemDescription", nearbyItems.get(i).getItemDescription());
////                        intent.putExtra("ItemId", nearbyItems.get(i).getItemId());
////                        startActivity(intent);
////                    }
////                }
//
//            }
//
//            @Override
//            public void onSearchCleared() {
//                search.clearResults();
//            }
//
//        });
//    }


    class RequestItem extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    class DynamicSearch extends AsyncTask<String, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(String... params) {
            //SERVER CODE TO GET SEARCH ITEMS WITH STRING
            return null;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);
            searchResults = items;
            SearchResult option;
            if (searchResults != null) {
                for (Item currentItem : searchResults) {
                    if (currentItem.getItemType() == "electronics") {
                         option = new SearchResult(currentItem.getItemName(), getResources().getDrawable(R.mipmap.electronics), "" + currentItem.getItemId());
                        search.addSearchable(option);
                    } else if (currentItem.getItemType() == "household") {
                         option = new SearchResult(currentItem.getItemName(), getResources().getDrawable(R.mipmap.home), "" + currentItem.getItemId());
                        search.addSearchable(option);
                    } else if (currentItem.getItemType() == "tools") {
                         option = new SearchResult(currentItem.getItemName(), getResources().getDrawable(R.mipmap.tools), "" + currentItem.getItemId());
                        search.addSearchable(option);
                    } else if (currentItem.getItemType() == "books") {
                         option = new SearchResult(currentItem.getItemName(), getResources().getDrawable(R.mipmap.books), "" + currentItem.getItemId());
                        search.addSearchable(option);
                    }
                }
            }
        }
    }

    public static int getPixelsFromDp(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }
}








