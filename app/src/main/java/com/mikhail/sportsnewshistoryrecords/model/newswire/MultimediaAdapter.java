package com.mikhail.sportsnewshistoryrecords.model.newswire;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;
import com.mikhail.sportsnewshistoryrecords.model.newswire.NytSportsMultimedia;

import java.io.IOException;

/**
 * Adapter that handles empty Multimedia array of objects from NYT News Wire API
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
