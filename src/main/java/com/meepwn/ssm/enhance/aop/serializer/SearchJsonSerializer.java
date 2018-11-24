package com.meepwn.ssm.enhance.aop.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchJsonSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        System.out.println("=======------");
//        jsonGenerator.writeString("");
        jsonGenerator.writeObject(new JsonNullDef());
    }

    class JsonNullDef {

        private List<String> def = new ArrayList<>();

        public List<String> getDef() {
            return def;
        }

        public void setDef(List<String> def) {
            this.def = def;
        }

    }

}
