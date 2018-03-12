package com.example.android.ibidsera.model.migrate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fahmi Hakim on 08/03/2018.
 * for SERA
 */

public class MyModel {

    private List<Databean> data = new ArrayList<>();


    public  List<Databean> getData() {
        return data;
    }


    public static class Databean {
        private int num;
        private String name;

        public int getNum() {
            return num;
        }

        public void setNum(int number) {
            this.num = number;
        }

        public String getValue() {
            return name;
        }

        public void setValue(String value) {
            this.name = name;
        }

    }
}
