package com.cs639.unofficialbronxzooaudiotourguide;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * MainActivity. The Guide XML is transformed into internal data in this class and
 * entered into the ViewModel AllData where all fragments and activities can
 * easily access the data.
 *
 * @author Tom
 */
public class MainActivity extends AppCompatActivity {
    ArrayList<Animal> animals;
    ArrayList<Structure> structures;
    ArrayList<AnimalContainerStructure> animalContainerStructures;

    public int ScreenWidth;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AllAppData viewModel = new ViewModelProvider(this).get(AllAppData.class);
        viewModel.getUser().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                // update ui.
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DisplayMetrics myMetric = new DisplayMetrics();
        getDisplay().getRealMetrics(myMetric);
        viewModel.setScreenSize(myMetric.widthPixels);
        XMLParse();
        viewModel.setAnimals(animals);
        viewModel.setStructures(structures);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Imports data from guide.xml into collections of Animals and Structures, and places them in
     * a ViewModel that every Activity / Fragment in the APK can access.
     */
    private void XMLParse() {
        XmlPullParserFactory parserFactory;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(getAssets().open("guide.xml"));
            doc.getDocumentElement().normalize();
            XmlParseProcessor(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Where the document becomes java data
     */
    private void XmlParseProcessor(Document guide) {
        animals = new ArrayList<Animal>();
        structures = new ArrayList<Structure>();
        animalContainerStructures  = new ArrayList<AnimalContainerStructure>();
        NodeList nList = guide.getElementsByTagName("animal");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Animal myAnimal = new Animal();
                Element elem = (Element) nNode;

                //Adds each root element to the new animal
                myAnimal.setId(Integer.parseInt(elem.getAttribute("id")));
                myAnimal.setParentStructure(Integer.parseInt(elem.getElementsByTagName("parentstructure").item(0).getTextContent()));
                myAnimal.setZooName(elem.getElementsByTagName("zooname").item(0).getTextContent());
                myAnimal.setBinomialNomenclature(elem.getElementsByTagName("binomialnomenclature").item(0).getTextContent());
                myAnimal.setAnimalClass(elem.getElementsByTagName("class").item(0).getTextContent());
                myAnimal.setFamily(elem.getElementsByTagName("family").item(0).getTextContent());
                myAnimal.setConservationStatus(elem.getElementsByTagName("IUCNConservationStatus").item(0).getTextContent());
                myAnimal.setNaturalLocation(elem.getElementsByTagName("naturallocation").item(0).getTextContent());

                //Code for adding the list of paragraphs
                Node paragraphsNode = elem.getElementsByTagName("originalcontent").item(0);
                NodeList paragraphList = paragraphsNode.getChildNodes();
                ArrayList<String> paragraphArray = new ArrayList<>();
                for (int ip = 0; ip < paragraphList.getLength(); ip++) {
                    if (paragraphList.item(ip).getNodeType() == Node.ELEMENT_NODE) {
                        paragraphArray.add(paragraphList.item(ip).getTextContent());
                    }
                }
                myAnimal.setDescription(paragraphArray);

                //Code for adding the list of commonnames
                NodeList commonNameList = elem.getElementsByTagName("commonname");
                ArrayList<String> commonNamesArray = new ArrayList<>();
                for (int ip = 0; ip < commonNameList.getLength(); ip++) {
                    if (commonNameList.item(ip).getNodeType() == Node.ELEMENT_NODE) {
                        commonNamesArray.add(commonNameList.item(ip).getTextContent());
                    }
                }
                myAnimal.setCommonNames(commonNamesArray);

                //Code for associating all URLs to animal
                Node urlsNode = elem.getElementsByTagName("readinglist").item(0);
                NodeList urlList = urlsNode.getChildNodes();
                ArrayList<String> urlArray = new ArrayList<String>();
                ArrayList<String> urlNameArray = new ArrayList<String>();
                for (int ip = 0; ip < urlList.getLength(); ip++) {
                    if (urlList.item(ip).getNodeType() == Node.ELEMENT_NODE) {
                        NodeList multipleUrlParts = urlList.item(ip).getChildNodes();
                        String nameURL = multipleUrlParts.item(1).getTextContent();
                        String url = multipleUrlParts.item(3).getTextContent();
                        if (nameURL.equals("eol")) {
                            myAnimal.setEolLink(url);
                        } else if (nameURL.equals("wikipedia")) {
                            myAnimal.setWikiLink(url);
                        } else {
                            urlArray.add(url);
                            urlNameArray.add(nameURL);
                        }
                    }
                }
                myAnimal.setOtherResourceNames(urlNameArray);
                myAnimal.setOtherResourceLinks(urlArray);

                //Code for associating all locations to animal
                Node locsNode = elem.getElementsByTagName("viewingpoints").item(0);
                NodeList locList = locsNode.getChildNodes();
                ArrayList<Location> locArray = new ArrayList<Location>();
                for (int ip = 0; ip < locList.getLength(); ip++) {
                    if (locList.item(ip).getNodeType() == Node.ELEMENT_NODE) {
                        NodeList multipleLocParts = locList.item(ip).getChildNodes();
                        String lat = multipleLocParts.item(1).getTextContent();
                        String longit = multipleLocParts.item(3).getTextContent();
                        Location newLocation = new Location("");
                        newLocation.setLongitude(Double.parseDouble(longit));
                        newLocation.setLatitude(Double.parseDouble(lat));
                        locArray.add(newLocation);
                    }
                }
                myAnimal.setOtherResourceNames(urlNameArray);
                myAnimal.setOtherResourceLinks(urlArray);
                myAnimal.setViewingPoints(locArray);
                animals.add(myAnimal);
            }
        }
        NodeList nListStuct = guide.getElementsByTagName("structure");
        for (int i = 0; i < nListStuct.getLength(); i++) {
            Node nNode = nListStuct.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Structure myStructure = new Structure();
                Element elem = (Element) nNode;
                myStructure.setId(Integer.parseInt(elem.getAttribute("id")));
                myStructure.setStructureName(elem.getElementsByTagName("stucttitle").item(0).getTextContent());

                //Code for adding the list of paragraphs
                Node paragraphsNode = elem.getElementsByTagName("originalcontent").item(0);
                NodeList paragraphList = paragraphsNode.getChildNodes();
                ArrayList<String> paragraphArray = new ArrayList<>();
                for (int ip = 0; ip < paragraphList.getLength(); ip++) {
                    if (paragraphList.item(ip).getNodeType() == Node.ELEMENT_NODE) {
                        paragraphArray.add(paragraphList.item(ip).getTextContent());
                    }
                }
                myStructure.setDescription(paragraphArray);

                //Code for associating all secondary resources to structures
                Node urlsNode = elem.getElementsByTagName("secondaryresourcelist").item(0);
                NodeList urlList = urlsNode.getChildNodes();
                ArrayList<String> urlArray = new ArrayList<String>();
                ArrayList<String> urlNameArray = new ArrayList<String>();
                for (int ip = 0; ip < urlList.getLength(); ip++) {
                    if (urlList.item(ip).getNodeType() == Node.ELEMENT_NODE) {
                        NodeList multipleUrlParts = urlList.item(ip).getChildNodes();
                        String nameURL = multipleUrlParts.item(1).getTextContent();
                        String url = multipleUrlParts.item(3).getTextContent();
                        urlArray.add(url);
                        urlNameArray.add(nameURL);
                    }
                }
                myStructure.setSecondaryResourceNames(urlNameArray);
                myStructure.setSecondaryResourceLinks(urlArray);


                //Code for associating all secondary resources to structures
                Node urlsNode1 = elem.getElementsByTagName("primaryresourcelist").item(0);
                NodeList urlList1 = urlsNode1.getChildNodes();
                ArrayList<String> urlArray1 = new ArrayList<String>();
                ArrayList<String> urlNameArray1 = new ArrayList<String>();
                for (int ip = 0; ip < urlList1.getLength(); ip++) {
                    if (urlList1.item(ip).getNodeType() == Node.ELEMENT_NODE) {
                        NodeList multipleUrlParts = urlList1.item(ip).getChildNodes();
                        String nameURL = multipleUrlParts.item(1).getTextContent();
                        String url = multipleUrlParts.item(3).getTextContent();
                        urlArray1.add(url);
                        urlNameArray1.add(nameURL);
                    }
                }
                myStructure.setPrimaryResourceNames(urlNameArray);
                myStructure.setPrimaryResourceLinks(urlArray);

                //Code for associating all locations to animal
                Node locsNode = elem.getElementsByTagName("viewingpoints").item(0);
                NodeList locList = locsNode.getChildNodes();
                ArrayList<Location> locArray = new ArrayList<Location>();
                for (int ip = 0; ip < locList.getLength(); ip++) {
                    if (locList.item(ip).getNodeType() == Node.ELEMENT_NODE) {
                        NodeList multipleLocParts = locList.item(ip).getChildNodes();
                        String lat = multipleLocParts.item(1).getTextContent();
                        String longit = multipleLocParts.item(3).getTextContent();
                        Location newLocation = new Location("");
                        newLocation.setLongitude(Double.parseDouble(longit));
                        newLocation.setLatitude(Double.parseDouble(lat));
                        locArray.add(newLocation);
                    }
                }
                myStructure.setViewingPoints(locArray);
                structures.add(myStructure);

                //Code to load animal containing structures
                NodeList nListContainers = guide.getElementsByTagName("animalcontainingstructure");
                for (int x = 0; x < nListContainers.getLength(); x++) {
                    Node nListContainer = nListContainers.item(x);
                    AnimalContainerStructure animalContainerStructure = new AnimalContainerStructure();
                    Element elemContainer = (Element) nListContainer;
                    Log.i("TOMDEBUG", "gets this far" + elemContainer.getAttribute("name"));
                    animalContainerStructure.setId(Integer.parseInt(elemContainer.getAttribute("id")));
                    animalContainerStructure.setContainerName(elemContainer.getAttribute("name"));
                    String myLong = elemContainer.getElementsByTagName("longitude").item(0).getTextContent();
                    String myLat = elemContainer.getElementsByTagName("latitude").item(0).getTextContent();
                    Location myLocation = new Location("");
                    myLocation.setLatitude(Double.parseDouble(myLat));
                    myLocation.setLongitude(Double.parseDouble(myLong));
                    animalContainerStructure.setViewingPoints(myLocation);
                    animalContainerStructures.add(animalContainerStructure);
//                    Log.i("TOMDEBUG", "structcontainer id:" + animalContainerStructure.getId());
//                    Log.i("TOMDEBUG", "structcontainer name:" + animalContainerStructure.getContainerName());
//                    Log.i("TOMDEBUG", "structcontainer location:" + animalContainerStructure.getViewingPoints());


                }

                //                Log.i("TOMDEBUG", "struct id:" + myStructure.getId());
//                Log.i("TOMDEBUG", "structname " + myStructure.getStructureName());
//                for (int temp = 0; temp < paragraphArray.size(); temp++) {
//                    Log.i("TOMDEBUG", "Paragraph " + temp + ":" + paragraphArray.get(temp));
//                }
//                for (int temp = 0; temp < urlArray.size(); temp++)
//                    Log.i("TOMDEBUG", "URLs Names " + temp + ":" + urlNameArray.get(temp) + " " + urlArray.get(temp));
//                for(int temp = 0; temp < urlArray1.size(); temp++)
//                    Log.i("TOMDEBUG","URLs Names " + temp + ":" + urlNameArray1.get(temp) + " " + urlArray1.get(temp));

            }

        }
    }

// Code for testing the completeness of imported animals
//                Log.i("TOMDEBUG","animal id:" + myAnimal.getId());
//                Log.i("TOMDEBUG","animal zooname: "+ myAnimal.getZooName());
//                Log.i("TOMDEBUG","animal binom: "+ myAnimal.getBinomialNomenclature());
//                Log.i("TOMDEBUG","animal class: " + myAnimal.getAnimalClass());
//                Log.i("TOMDEBUG","animal family: " + myAnimal.getFamily());
//                Log.i("TOMDEBUG","animal natural locale: " + myAnimal.getNaturalLocation());
//                Log.i("TOMDEBUG","animal conversation: " + myAnimal.getConservationStatus());
//                Log.i("TOMDEBUG","wikipedia link: " + myAnimal.getWikiLink());
//                Log.i("TOMDEBUG","eol link: " + myAnimal.getEolLink());
//                for(int temp = 0; temp < paragraphArray.size(); temp++){
//                    Log.i("TOMDEBUG","Paragraph " + temp + ":" + paragraphArray.get(temp));
//                }
//                for(int temp = 0; temp < commonNamesArray.size(); temp++){
//                    Log.i("TOMDEBUG","Common Names " + temp + ":" + commonNamesArray.get(temp));
//                }
//                for(int temp = 0; temp < urlArray.size(); temp++)
//                    Log.i("TOMDEBUG","URLs Names " + temp + ":" + urlNameArray.get(temp) + " " + urlArray.get(temp));
//                }


}
