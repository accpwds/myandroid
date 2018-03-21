package com.example.android.handler;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Administrator on 2018/3/16.
 */

public class ContentHandler extends DefaultHandler {

    private String nodeName;

    private StringBuilder title;

    private StringBuilder artist;

    private StringBuilder country;

    private StringBuilder price;

    private StringBuilder year;

    private static final String TAG="ContentHandler";

    @Override
    public void startDocument() throws SAXException {

        title = new StringBuilder();
        artist = new StringBuilder();
        country = new StringBuilder();
        price = new StringBuilder();
        year = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        nodeName = localName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ("cd".equalsIgnoreCase(localName)){
            Log.d(TAG,"title is " + title.toString().trim());
            Log.d(TAG,"artist is " + artist.toString().trim());
            Log.d(TAG,"country is " + country.toString().trim());
            Log.d(TAG,"price is " + price.toString().trim());
            Log.d(TAG,"year is " + year.toString().trim());

            title.setLength(0);
            artist.setLength(0);
            country.setLength(0);
            price.setLength(0);
            year.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if ("title".equalsIgnoreCase(nodeName)){
            title.append(ch,start,length);
        } else if ("artist".equalsIgnoreCase(nodeName)){
            artist.append(ch,start,length);
        } else if ("country".equalsIgnoreCase(nodeName)){
            country.append(ch,start,length);
        } else if ("price".equalsIgnoreCase(nodeName)){
            price.append(ch,start,length);
        } else if ("year".equalsIgnoreCase(nodeName)){
            year.append(ch,start,length);
        }
    }
}
