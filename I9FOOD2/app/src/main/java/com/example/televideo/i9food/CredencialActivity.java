package com.example.televideo.i9food;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CredencialActivity extends AppCompatActivity {

    private Toolbar mToolbar; //actionbar
    private Toolbar mToolbarBotao; //stand alone
    private Bitmap bitmap_qrcode;
    private Bitmap bitmap_credencial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credencial);


        mToolbar=(Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("CREDENCIAL");
        //mToolbar.setSubtitle("GOURMET");
        setSupportActionBar(mToolbar);


        mToolbarBotao=(Toolbar)findViewById(R.id.inc_tb_botao);

        mToolbarBotao.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuitem) {
                Intent it=null;
                switch (menuitem.getItemId()){
                    case R.id.action_busca:
                        it= new Intent(getContext(),MainActivity.class);
                        startActivity(it);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                    case R.id.action_pedidos:
                        it= new Intent(getContext(),PedidosActivity.class);
                        startActivity(it);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                    case R.id.action_eventos:
                        it= new Intent(getContext(),EventoActivity.class);
                        startActivity(it);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                    case R.id.action_perfil:
                        it= new Intent(getContext(),PerfilActivity.class);
                        startActivity(it);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        break;
                }
                return true;
            }
        });
        mToolbarBotao.inflateMenu(R.menu.menu_bottom);

        String texto = "Nome: Roney Braz; CPF: 10647874501";
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(texto, BarcodeFormat.QR_CODE,450,450);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap_qrcode = barcodeEncoder.createBitmap(bitMatrix);

        }catch (WriterException e){
            e.printStackTrace();
        }



        Resources mResources = getResources();
        bitmap_credencial = BitmapFactory.decodeResource(mResources, R.drawable.credencial);


        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder( bitmap_credencial.getWidth(),  bitmap_credencial.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);


        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint(paint);

        bitmap_credencial = Bitmap.createScaledBitmap( bitmap_credencial,  bitmap_credencial.getWidth(),  bitmap_credencial.getHeight(), true);

        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap_credencial, 0, 0 , null);

        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        canvas.drawText("Roney Braz",360,800,paint);


        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap_qrcode, 370, 920 , null);




        document.finishPage(page);



        // write the document content
        String targetPdf = "/sdcard/I9FOOD.pdf";
        File filePath = new File(targetPdf);

        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Credencial Gravada com Sucesso!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        // close the document
        document.close();




       /////////////////////////////////////////////////////////////

       // File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(filePath), "application/pdf");

        Intent intent = Intent.createChooser(target, "Abrir arquivo");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            //Caso o usuário não tenha um visualizador de PDF, instrua-o aqui a baixar
        }





}
    private Context getContext()
    {
        return this;
    }
}
