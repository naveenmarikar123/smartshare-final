package com.example.naveenmarikar.smartshare1;

/**
 * Created by naveenmarikar on 27/02/15.
 */
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naveenmarikar.smartshare1.Product;

public class Search extends Fragment {

    View myFragmentView;
    SearchView search;
    ImageButton buttonBarcode;
    ImageButton buttonAudio;
    Typeface type;
    ListView searchResults;
    String found = "N";

    public static Search newInstance() {
        Search fragment = new Search();
        return fragment;
    }



    //This arraylist will have data as pulled from server. This will keep cumulating.
    ArrayList<Product> productResults = new ArrayList<Product>();
    //Based on the search string, only filtered products will be moved here from productResults
    ArrayList<Product> filteredProductResults = new ArrayList<Product>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


        //define a typeface for formatting text fields and listview.

        //type = Typeface.createFromAsset(activity_main.getAssets(), "fonts/book.TTF");
        final View rootView = inflater.inflate(R.layout.searchmain, container, false);
        search = (SearchView) rootView.findViewById(R.id.searchView1);
        search.setQueryHint("Enter Item To Search..");

        searchResults = (ListView) rootView.findViewById(R.id.listview_search);

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        search.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 3) {
                    searchResults.setVisibility(rootView.VISIBLE);
                    myAsyncTask m = (myAsyncTask) new myAsyncTask().execute(newText);
                } else {
                    searchResults.setVisibility(rootView.INVISIBLE);
                }
                return false;
            }
        });
        return rootView;
    }

    public void filterProductArray(String newText)
    {
        String pName;
        filteredProductResults.clear();
        for (int i = 0; i < productResults.size(); i++)
        {
            pName = productResults.get(i).getProductName().toLowerCase();
            if ( pName.contains(newText.toLowerCase()) ||
                    productResults.get(i).getProductBarcode().contains(newText))
            {
                filteredProductResults.add(productResults.get(i));

            }
        }

    }

    class myAsyncTask extends AsyncTask<String, Void, String>
    {
        JSONParser jParser;
        JSONArray productList;
        String url=new String();
        String textSearch;
        ProgressDialog pd;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            productList=new JSONArray();
            jParser = new JSONParser();
            pd= new ProgressDialog(getActivity());
            pd.setCancelable(false);
            pd.setMessage("Finding Items...");
            pd.getWindow().setGravity(Gravity.CENTER);
            pd.show();
        }

        @Override
        protected String doInBackground(String... sText) {

            url="http://lawgo.in/lawgo/products/user/1/search/"+sText[0];
            String returnResult = getProductList(url);
            this.textSearch = sText[0];
            return returnResult;

        }

        public String getProductList(String url)
        {

            Product tempProduct = new Product();
            String matchFound = "N";
            //productResults is an arraylist with all product details for the search criteria
            //productResults.clear();


            try {



                JSONObject json = jParser.getJSONFromUrl(url);

                productList = json.getJSONArray("ProductList");

                //parse date for dateList
                for(int i=0;i<productList.length();i++)
                {
                    tempProduct = new Product();

                    JSONObject obj=productList.getJSONObject(i);

                    tempProduct.setProductCode(obj.getString("ProductCode"));
                    tempProduct.setProductName(obj.getString("ProductName"));
                    tempProduct.setProductGrammage(obj.getString("ProductGrammage"));
                    tempProduct.setProductBarcode(obj.getString("ProductBarcode"));
                    tempProduct.setProductDivision(obj.getString("ProductCatCode"));
                    tempProduct.setProductDepartment(obj.getString("ProductSubCode"));
                    tempProduct.setProductMRP(obj.getString("ProductMRP"));
                    tempProduct.setProductBBPrice(obj.getString("ProductBBPrice"));

                    for (int j=0; j < productResults.size();j++)
                    {

                        if (productResults.get(j).getProductCode().equals(tempProduct.getProductCode()))
                        {
                            matchFound = "Y";
                        }
                    }

                    if (matchFound == "N")
                    {
                        productResults.add(tempProduct);
                    }

                }

                return ("OK");

            } catch (Exception e) {
                e.printStackTrace();
                return ("Exception Caught");
            }
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

           if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(getActivity(), "Unable to connect to server,please try later", Toast.LENGTH_LONG).show();

                pd.dismiss();
            }
            else {


               //calling this method to filter the search results from productResults and move them to
               //filteredProductResults
               filterProductArray(textSearch);
               searchResults.setAdapter(new SearchResultsAdapter(getActivity(), filteredProductResults));
               pd.dismiss();
           }
        }

    }

    class SearchResultsAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;

        private ArrayList<Product> productDetails = new ArrayList<Product>();
        int count;
        Typeface type;
        Context context;

        //constructor method
        public SearchResultsAdapter(Context context, ArrayList<Product> product_details) {

            layoutInflater = LayoutInflater.from(context);

            this.productDetails = product_details;
            this.count = product_details.size();
            this.context = context;

        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int arg0) {
            return productDetails.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            Product tempProduct = productDetails.get(position);

            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.searchresults, null);
                holder = new ViewHolder();
                holder.product_name = (TextView) convertView.findViewById(R.id.product_name);
                holder.product_rating = (TextView) convertView.findViewById(R.id.product_rating);
                holder.product_status = (TextView) convertView.findViewById(R.id.product_status);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.product_name.setText(tempProduct.getProductName());



            return convertView;
        }
    }

    static class ViewHolder
    {
        TextView product_name;
        TextView product_rating;
        TextView product_status;

    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity2) activity).onSectionAttached(3);
    }

}