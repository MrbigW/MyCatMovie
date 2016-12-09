package com.atsgg.mycatmovie.adapter.found;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsgg.mycatmovie.R;
import com.atsgg.mycatmovie.bean.found.HeadBean;
import com.atsgg.mycatmovie.bean.found.ListBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */
public class FindRecyclerViewAdapter extends RecyclerView.Adapter {
    /**
     * 总共五种类型
     */
    private static final int HEAD_GRIDVIEW = 1;
    private static final int ONE_IMAGE_TEXT_RIGHT = 2;
    private static final int THREE_IMAGE = 3;
    private static final int THREE_GRID_IMAGE = 4;
    private static final int ONE_IMAGE_TEXT_TOP = 5;


    private static final String TAG = "findadapter";
    private final Context mContext;

    //第一种类型--GirdView的数据
    private List<HeadBean.DataBean> headDatas = new ArrayList<>();
    private ListBean mListBean;
    private List<ListBean.DataBean.FeedsBean> mListData = new ArrayList<>();
    private int mPostion;

    public FindRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return HEAD_GRIDVIEW;
        } else {
            int style = mListData.get(position-1).getStyle();
            if (style == 2) {
                return ONE_IMAGE_TEXT_RIGHT;
            } else if (style == 3) {
                return THREE_IMAGE;
            } else if (style == 4) {
                return THREE_GRID_IMAGE;
            } else {
                return ONE_IMAGE_TEXT_TOP;
            }
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_GRIDVIEW) {
            View itemView = View.inflate(mContext, R.layout.find_item_head_gridview, null);
            return new HeadGridviewHolder(itemView);
        } else if (viewType == ONE_IMAGE_TEXT_RIGHT) {
            View itemView = View.inflate(mContext, R.layout.find_item_one_image_text_right, null);
            return new OneImageTextRightHolder(itemView);
        } else if (viewType == THREE_IMAGE) {
            View itemView = View.inflate(mContext, R.layout.find_item_three_image, null);
            return new ThreeImageHolder(itemView);
        } else if (viewType == THREE_GRID_IMAGE) {
            View itemView = View.inflate(mContext, R.layout.find_item_three_grid_image, null);
            return new ThreeGridImageHolder(itemView);
        } else if (viewType == ONE_IMAGE_TEXT_TOP) {
            View itemView = View.inflate(mContext, R.layout.find_item_one_image_text_top, null);
            return new OneImageTextTopHolder(itemView);
        }


        return null;
    }


    public void refreshHead(List<HeadBean.DataBean> datas) {
        if (datas != null && datas.size() > 0) {
            headDatas.clear();
            headDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEAD_GRIDVIEW) {
            HeadGridviewHolder headHridviewHolder = (HeadGridviewHolder) holder;
            headHridviewHolder.setData(headDatas);
        } else if (getItemViewType(position) == ONE_IMAGE_TEXT_RIGHT) {
                OneImageTextRightHolder oneImageTextRightHolder = (OneImageTextRightHolder) holder;
                oneImageTextRightHolder.setData(mListData, position);
        } else if (getItemViewType(position) == THREE_IMAGE) {
            ThreeImageHolder threeImageHolder = (ThreeImageHolder) holder;
            threeImageHolder.setData(mListData, position);
        } else if (getItemViewType(position) == THREE_GRID_IMAGE) {
            ThreeGridImageHolder threeImageGrridHolder = (ThreeGridImageHolder) holder;
            threeImageGrridHolder.setData(mListData, position);
        } else if (getItemViewType(position) == ONE_IMAGE_TEXT_TOP) {
            OneImageTextTopHolder oneImageTextTopHolder = (OneImageTextTopHolder) holder;
            oneImageTextTopHolder.setData(mListData, position);
        }


    }

    //
    @Override
    public int getItemCount() {
        return mListData.size() + 1;
    }

    //得其他四种类型的数据
    public void refreshList(ListBean listBean) {
        mListBean = listBean;
        List<ListBean.DataBean.FeedsBean> feedsList = listBean.getData().getFeeds();
        if (feedsList.size() > 0 && feedsList != null) {
            mListData.clear();
            mListData.addAll(feedsList);
            notifyDataSetChanged();
        }
    }

    public void refreshListMore(ListBean listBean) {
        mListData.addAll(listBean.getData().getFeeds());
        notifyDataSetChanged();
    }

    private class HeadGridviewHolder extends RecyclerView.ViewHolder {
        private GridView find_gridview;

        public HeadGridviewHolder(View itemView) {
            super(itemView);
            find_gridview = (GridView) itemView.findViewById(R.id.find_gridview);
        }

        public void setData(List<HeadBean.DataBean> headDatas) {
            HeadGridViewAdapter headGridViewAdapter = new HeadGridViewAdapter(mContext);
            find_gridview.setAdapter(headGridViewAdapter);
            headGridViewAdapter.refresh(headDatas);
        }
    }

    private class OneImageTextRightHolder extends RecyclerView.ViewHolder {
        private TextView tvDate;
        private ImageView ivImage;
        private TextView tvTitle;
        private TextView tvNickname;
        private TextView tvViewCount;
        private TextView tvCommontCount;

        public OneImageTextRightHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivImage = (ImageView) itemView.findViewById(R.id.find_iv_image);
            tvNickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            tvViewCount = (TextView) itemView.findViewById(R.id.tv_view_count);
            tvCommontCount = (TextView) itemView.findViewById(R.id.tv_commont_count);
        }

        public void setData(List<ListBean.DataBean.FeedsBean> mListData, int position) {
            position--;
            ListBean.DataBean.FeedsBean feedsBean = mListData.get(position);
            tvNickname.setText(feedsBean.getUser().getNickName());
            tvTitle.setText(feedsBean.getTitle());
//            tvCommontCount.setText(feedsBean.getCommentCount());
//            tvViewCount.setText(feedsBean.getViewCount());
            Glide.with(mContext).load(feedsBean.getImages().get(0).getUrl()).into(ivImage);

            //相关逻辑处理
            long mtime = feedsBean.getTime();//得到服务器的事件
            long systemTime = System.currentTimeMillis();//得到当前系统时间
            if (mtime >= systemTime) {
                tvDate.setText("今天");
            } else {
                tvDate.setText("昨天");
            }

        }
    }

    private class ThreeImageHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView ivImage1;
        private ImageView ivImage2;
        private ImageView ivImage3;

        public ThreeImageHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivImage1 = (ImageView) itemView.findViewById(R.id.iv_image1);
            ivImage2 = (ImageView) itemView.findViewById(R.id.iv_image2);
            ivImage3 = (ImageView) itemView.findViewById(R.id.iv_image3);
        }

        public void setData(List<ListBean.DataBean.FeedsBean> mListData, int position) {
            position--;
            ListBean.DataBean.FeedsBean feedsBean = mListData.get(position);
            tvTitle.setText(feedsBean.getTitle());
            Glide.with(mContext).load(feedsBean.getImages().get(0).getUrl()).into(ivImage1);
            Glide.with(mContext).load(feedsBean.getImages().get(1).getUrl()).into(ivImage2);
            Glide.with(mContext).load(feedsBean.getImages().get(2).getUrl()).into(ivImage3);
        }
    }

    private class ThreeGridImageHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView ivImage1;
        private ImageView ivImage2;
        private ImageView ivImage3;

        public ThreeGridImageHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivImage1 = (ImageView) itemView.findViewById(R.id.iv_image1);
            ivImage2 = (ImageView) itemView.findViewById(R.id.iv_image2);
            ivImage3 = (ImageView) itemView.findViewById(R.id.iv_image3);
        }

        public void setData(List<ListBean.DataBean.FeedsBean> mListData, int position) {
            position--;
            ListBean.DataBean.FeedsBean feedsBean = mListData.get(position);
            tvTitle.setText(feedsBean.getTitle());
            Glide.with(mContext).load(feedsBean.getImages().get(0).getUrl()).into(ivImage1);
            Glide.with(mContext).load(feedsBean.getImages().get(1).getUrl()).into(ivImage2);
            Glide.with(mContext).load(feedsBean.getImages().get(2).getUrl()).into(ivImage3);
        }
    }

    private class OneImageTextTopHolder extends RecyclerView.ViewHolder {
       private TextView tvTitle;
       private ImageView ivImage;

        public OneImageTextTopHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        }

        public void setData(List<ListBean.DataBean.FeedsBean> mListData, int position) {
            position--;
            ListBean.DataBean.FeedsBean feedsBean = mListData.get(position);
            tvTitle.setText(feedsBean.getTitle());
            Glide.with(mContext).load(feedsBean.getImages().get(0).getUrl()).into(ivImage);
        }
    }
}
