package kr.iuu.samplepdfview;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = editText.getText().toString();

                if (filename.length() > 0) {
                    openPDF(filename.trim());
                } else {
                    Toast.makeText(getApplicationContext(), "PDF 파일명을 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openPDF(String filename) {
        String sdcardFolder = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filepath = sdcardFolder + File.separator + filename;
        File file = new File(filepath);

        if(file.exists()){
            Uri uri = Uri.fromFile(file);

            Toast.makeText(this, uri.toString(),Toast.LENGTH_SHORT).show();;

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");  //MIME

            try{
                startActivity(intent);
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(this, "PDF 파일을 보기 위한 뷰어 앱이 없습니다.",Toast.LENGTH_SHORT).show();;
            }
        }else{
            Toast.makeText(this, "PDF 파일이 없습니다.",Toast.LENGTH_SHORT).show();;
        }
    }
}
