package com.example.televideo.i9food;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Televideo on 21/11/2017.
 */

public class EventoAdapter extends ArrayAdapter<Eventos> {
    Context contexto;
    ArrayList<Eventos> eventos;


    public EventoAdapter(Context context, int resource,ArrayList<Eventos> objects){
        super(context, resource, objects);
        this.contexto = context;
        this.eventos = objects;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View linhaView = LayoutInflater.from(contexto).inflate(R.layout.linha_eventos, parent, false);


        TextView Titulo=(TextView)linhaView.findViewById(R.id.linha_eventos_Titulo);
        TextView Data = (TextView)linhaView.findViewById(R.id.linha_eventos_data);
        TextView Local = (TextView)linhaView.findViewById(R.id.linha_eventos_local);
        TextView Descricao = (TextView)linhaView.findViewById(R.id.linha_eventoDescricao);
        ImageView Imagem=(ImageView) linhaView.findViewById(R.id.linha_Eventos_img);
        ImageView bntadd_evento=(ImageView) linhaView.findViewById(R.id.linha_evento_add);



        Titulo.setText(eventos.get(position).getNome_Evento());
        Descricao.setText(eventos.get(position).getDesc_Evento());
        Data.setText(eventos.get(position).getData_Evento());
        Local.setText(eventos.get(position).getLocal_Evento());
        Imagem.setImageResource(eventos.get(position).getImagem());


        bntadd_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                // create a new document
                PdfDocument document = new PdfDocument();

                // crate a page description
                PdfDocument.PageInfo pageInfo =
                        new PdfDocument.PageInfo.Builder(100, 100, 1).create();

                // start a page
                PdfDocument.Page page = document.startPage(pageInfo);

                Canvas canvas = page.getCanvas();

                Paint paint = new Paint();
                paint.setColor(Color.BLACK);



                canvas.drawText("I9FOOD",28,20,paint);
                canvas.drawText("VISITANTE",18,70,paint);
                paint.setTextSize(5);
                //canvas.drawText("Roney",30,85,paint);



                // finish the page
                document.finishPage(page);



                // write the document content
                String targetPdf = "/sdcard/I9FOOD.pdf";
                File filePath = new File(targetPdf);
                try {
                    document.writeTo(new FileOutputStream(filePath));
                    Toast.makeText(contexto, "Credencial Gravada com Sucesso!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(contexto, "Erro "+e.toString(), Toast.LENGTH_SHORT).show();
                }

                // close the document
                document.close();

            }


        });





        return linhaView;



    }

}
