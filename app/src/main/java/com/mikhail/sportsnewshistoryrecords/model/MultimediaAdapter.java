package com.mikhail.sportsnewshistoryrecords.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.model.search.Multimedia;

import java.io.IOException;

/**
 * Created by Mikhail on 5/2/16.
 */
public class MultimediaAdapter extends TypeAdapter<NytSportsMultimedia[]> {

    @Override
    public void write(JsonWriter out, NytSportsMultimedia[] value) throws IOException {

        NytAPI.gson.getAdapter(NytSportsMultimedia[].class).write(out,value);

    }

    @Override
    public NytSportsMultimedia[] read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.BEGIN_ARRAY) {
            return NytAPI.gson.getAdapter(NytSportsMultimedia[].class).read(in);
        } else {
            in.skipValue();

            return new NytSportsMultimedia[0];
        }
    }
}
