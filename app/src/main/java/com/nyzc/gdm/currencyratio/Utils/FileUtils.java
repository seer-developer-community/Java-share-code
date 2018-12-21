package com.nyzc.gdm.currencyratio.Utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    Context context;

    public FileUtils(Context context) {
        this.context = context;
    }

    /**
     * 从assets中读取txt
     */

    public String readFromAssets() {
        String text = null;
        try {
            InputStream is = context.getAssets().open("brainkeydict.txt");
            text = readTextFromSDcard(is);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 按行读取txt
     *
     * @param is
     * @return
     * @throws Exception
     */

    public String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();

    }

}
