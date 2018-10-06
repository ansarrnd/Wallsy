package com.ansar.wallsy.util;

import android.net.Uri;
import android.text.TextUtils;

import com.ansar.wallsy.data.Image;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.ansar.wallsy.constants.Constant.WALLHAVEN_BASE_URL;

/**
 *
 * A class for parsing data from Wallhaven to usable objects.
 *
 */

public class Parser {

    public Parser() { }

    public static List<Image> parseImages(String data) {
        ArrayList<Image> images = new ArrayList<>();
        Document document = Jsoup.parse(data, WALLHAVEN_BASE_URL);
        Elements elements = document.select("section.thumb-listing-page figure");

        if (!elements.isEmpty()) {
            for (Element wrapperElement : elements) {

                //Get the resolution
                Element resElement = wrapperElement.getElementsByClass("thumb-info").first();
                String resolution = resElement.select("span.wall-res").text();

                //Get the image with link
                Element linkElement = wrapperElement.select("a.preview").first();
                String imagePageURL = linkElement.absUrl("href");
                Element localElement2 = wrapperElement.getElementsByTag("img").first();
                if (localElement2 != null) {
                    String thumbURL = localElement2.attr("data-src");
                    if ((!TextUtils.isEmpty(imagePageURL)) && (!TextUtils.isEmpty(thumbURL))) {
                        Uri pageUri = Uri.parse(imagePageURL);
                        String imageId = pageUri.getLastPathSegment();
                        Image localThumbnail = Image.create(imageId, thumbURL, imagePageURL, resolution);
                        images.add(localThumbnail);
                    }
                }
            }
        }
        return images;
    }

}
