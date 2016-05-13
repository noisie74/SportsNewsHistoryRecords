package com.mikhail.sportsnewshistoryrecords.model.search;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mikhail.sportsnewshistoryrecords.api.NytAPI;

import java.io.IOException;

/**
 * Adapter that handles empty Multimedia array of objects from NYT Article Search API
 */
public class MultimediaSearchAdapter extends TypeAdapter<Multimedia[]> {

    @Override
    public void write(JsonWriter out, Multimedia[] value) throws IOException {

        NytAPI.gson.getAdapter(Multimedia[].class).write(out,value);

    }

    @Override
    public Multimedia[] read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.BEGIN_ARRAY) {
            return NytAPI.gson.getAdapter(Multimedia[].class).read(in);
        } else {
            in.skipValue();

            return new Multimedia[0];
        }
    }
}
