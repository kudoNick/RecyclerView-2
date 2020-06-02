package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.recyclerview.R.layout.row_recyclerview;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Object> objects;
    List<Img> imgList;
    Context context;
    RowDataAdapter rowDataAdapter;

    public static final int TEXT = 0;
    public static final int IMAGE = 1;
    public static final int USER = 2;
    public static final int RECY = 3;


    public DataAdapter(List<Object> objects, Context context) {
        this.objects = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        switch (viewType) {
            case TEXT:
                View itemView0 = layoutInflater.inflate(R.layout.row_text, parent, false);
                return new TextViewHolder(itemView0);
            case IMAGE:
                View itemView1 = layoutInflater.inflate(R.layout.row_img, parent, false);
                return new ImageViewHolder(itemView1);
            case USER:
                View itemView2 = layoutInflater.inflate(R.layout.row_user,parent,false);
                return new UserViewHolder(itemView2);
            case RECY:
                View itemView3 = layoutInflater.inflate(row_recyclerview, parent, false);
                return new RecyclerViewHolder(itemView3);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TEXT:
                Text text = (Text) objects.get(position);
                TextViewHolder textViewHolder = (TextViewHolder) holder;
                textViewHolder.tvTextView.setText(text.getName());
                break;
            case IMAGE:
                Img img = (Img) objects.get(position);
                final ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                System.out.println("img " + img.getImg() );
                Picasso.get().load(img.getImg()).into(imageViewHolder.imgImage);
                break;
            case USER:
                User user = (User) objects.get(position);
                UserViewHolder userViewHolder = (UserViewHolder) holder;
                userViewHolder.tvName.setText(user.getName());
                userViewHolder.tvAddress.setText(user.getAddress());
                break;
            case RECY:
                 final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
                 imgList = new ArrayList<>();

                AndroidNetworking.get("https://api.github.com/users")
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                                System.out.println("Click img");
                                for (int i = 0; i <response.length() ; i++) {
                                    try {
                                        Img img = new Img(response.getJSONObject(i));
                                        imgList.add(img);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                rowDataAdapter = new RowDataAdapter(context, imgList);
                                recyclerViewHolder.rcvRowData.setAdapter(rowDataAdapter);
                            }
                            @Override
                            public void onError(ANError anError) {
                            }
                        });
                 recyclerViewHolder.rcvRowData.setHasFixedSize(true);
                 recyclerViewHolder.rcvRowData.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL, false));
                 recyclerViewHolder.rcvRowData.setNestedScrollingEnabled(false);

                 break;

        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (objects.get(position) instanceof Text) {
            return TEXT;
        }else if (objects.get(position) instanceof Img){
            return IMAGE;
        }else if (objects.get(position) instanceof User) {
            return USER;
        } else if (objects.get(position) instanceof Integer) {
            return RECY;
        }
        return -1;
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTextView;
        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTextView = itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "textView", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgImage;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.imgImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "ImageView", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvAddress;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "UserView", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rcvRowData;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvRowData = itemView.findViewById(R.id.rcv_row_data);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "RecyclerViewHolder", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
