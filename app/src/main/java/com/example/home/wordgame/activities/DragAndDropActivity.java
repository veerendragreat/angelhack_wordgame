package com.example.home.wordgame.activities;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.home.wordgame.R;
import com.example.home.wordgame.dtos.Words;
import com.example.home.wordgame.repository.DaoOperations;

import java.util.List;

public class DragAndDropActivity extends AppCompatActivity {
    private static int startIndex = 1;
    private static int endIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);

        DaoOperations daoOperations = new DaoOperations(DragAndDropActivity.this);
        daoOperations.open();
        int swap = endIndex;
        startIndex = endIndex;
        endIndex = swap + 5;

        List<Words> allwords = daoOperations.getAllwords(false, startIndex, endIndex);
        daoOperations.close();

        if (!allwords.isEmpty()) {
            int nSize = allwords.size();
            if (nSize <= 1) {
                Words word1=allwords.get(0);
                Button btWord1 = (Button) findViewById(R.id.word_bt1);
                btWord1.setTag("btWord1");
                btWord1.setText(word1.getWord());
                btWord1.setOnLongClickListener(new MyClickListener());
            }
            if (nSize <= 1) {
                Words word1 = allwords.get(0);
            Button btWord2 = (Button) findViewById(R.id.word_bt2);
                btWord2.setTag("btWord2");
                btWord2.setText(word1.getWord());
                btWord2.setOnLongClickListener(new MyClickListener());
            }
            Button btWord3 = (Button) findViewById(R.id.word_bt3);
            btWord3.setTag("btWord3");
            btWord3.setOnLongClickListener(new MyClickListener());
            Button btWord4 = (Button) findViewById(R.id.word_bt4);
            btWord4.setTag("btWord4");
            btWord4.setOnLongClickListener(new MyClickListener());
            Button btWord5 = (Button) findViewById(R.id.word_bt5);
            btWord5.setTag("btWord5");
            btWord5.setOnLongClickListener(new MyClickListener());
            findViewById(R.id.meaning_tv1).setOnDragListener(new MyDragListener());
            findViewById(R.id.meaning_tv2).setOnDragListener(new MyDragListener());
            findViewById(R.id.meaning_tv3).setOnDragListener(new MyDragListener());
            findViewById(R.id.meaning_tv4).setOnDragListener(new MyDragListener());
            findViewById(R.id.meaning_tv5).setOnDragListener(new MyDragListener());
        }
    }


    class MyClickListener implements View.OnLongClickListener {

        // called when the item is long-clicked
        @Override
        public boolean onLongClick(View view) {
            // TODO Auto-generated method stub

            // create it from the object's tag
            ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            view.startDrag(data, //data to be dragged
                    shadowBuilder, //drag shadow
                    view, //local data about the drag and drop operation
                    0   //no needed flags
            );


            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }


    class MyDragListener implements View.OnDragListener {
        Drawable normalShape = getResources().getDrawable(R.drawable.normal_shape);
        Drawable targetShape = getResources().getDrawable(R.drawable.target_shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {

            // Handles each of the expected events
            switch (event.getAction()) {

                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;

                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(targetShape);    //change the shape of the view
                    break;

                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(normalShape);    //change the shape of the view back to normal
                    break;

                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:

                    // if the view is the bottomlinear, we accept the drag item
                    if (v == v.findViewById(R.id.right_linear)) {

                        Context context = getApplicationContext();
                        Toast.makeText(context, "You can drop the word here" + v.getId() + v.toString(),
                                Toast.LENGTH_LONG).show();
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);

                        //change the text
                        TextView text = (TextView) v.findViewById(R.id.text);
                        text.setText("The item is dropped");

                        LinearLayout containView = (LinearLayout) v;
                        containView.addView(view);
                        view.setVisibility(View.VISIBLE);
                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Context context = getApplicationContext();
                        Toast.makeText(context, "You can't drop the image here" + v.getId() + v.toString(),
                                Toast.LENGTH_LONG).show();
                        break;
                    }
                    break;

                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackground(normalShape);    //go back to normal shape

                default:
                    break;
            }
            return true;
        }

    }
}
