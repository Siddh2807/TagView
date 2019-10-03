package com.example.tagview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CompoundButton;

import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.chip.Chip;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;
import com.hootsuite.nachos.validator.ChipifyingNachoValidator;
import com.ns.developer.tagview.entity.Tag;
import com.ns.developer.tagview.widget.TagCloudLinkView;
import com.skyhope.materialtagview.TagView;
import com.skyhope.materialtagview.enums.TagSeparator;

import java.util.ArrayList;
import java.util.List;

import me.originqiu.library.EditTag;

public class MainActivity extends AppCompatActivity implements
        SwitchCompat.OnCheckedChangeListener {

    TagView tagView;

    TagCloudLinkView tagCloudView;

    NachoTextView nachoTextView;

    private EditTag editTagView;
    private List<String> tagStrings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagStrings.add("Text1");
        tagStrings.add("Text2");
        tagStrings.add("Text3");
        tagStrings.add("Text4");
        tagStrings.add("Text5");

      tagView = (TagView) findViewById(R.id.flex_tv);

      tagView.addTagSeparator(TagSeparator.AT_SEPARATOR);
      tagView.getSelectedTags();

      String[] tagList = new String[]{"Penut Butter", "Butter"};
      tagView.setTagList(tagList);

      //-------------------

      tagCloudView = (TagCloudLinkView) findViewById(R.id.tag_cl_lk_vw);

        for (int i = 0; i < tagStrings.size(); i++) {

            String str=tagStrings.get(i);
            Log.e("data---",""+str);
            tagCloudView.add(new Tag(i,str));
            tagCloudView.drawTags();
        }


      tagCloudView.setOnTagSelectListener(new TagCloudLinkView.OnTagSelectListener() {
          @Override
          public void onTagSelected(Tag tag, int position) {

          }
      });

      tagCloudView.setOnTagDeleteListener(new TagCloudLinkView.OnTagDeleteListener() {
          @Override
          public void onTagDeleted(Tag tag, int position) {


          }
      });

     // ----------------------------
        editTagView = (EditTag) findViewById(R.id.et_tagView);

        editTagView.setTagList(tagStrings);

        editTagView.addTag("hello world!");
        editTagView.cancelLongPress();
        //-------------------------- Nacho Text View ------------------
        nachoTextView = (NachoTextView) findViewById(R.id.nacho_text_view);
        nachoTextView.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);
        nachoTextView.addChipTerminator(' ', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_TO_TERMINATOR);

        nachoTextView.setOnChipClickListener(new NachoTextView.OnChipClickListener() {
            @Override
            public void onChipClick(Chip chip, MotionEvent event) {
                Log.d("Chip click","----- "+chip.getText());
                removeChip();
            }
        });
    }

    public void removeChip()
    {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nachoTextView.getAllChips().size(); i++) {
         list.add(nachoTextView.getAllChips().get(i).toString());
        }
        nachoTextView.setText(list);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        editTagView.setEditable(isChecked);
    }
}