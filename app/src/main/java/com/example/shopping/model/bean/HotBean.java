package com.example.shopping.model.bean;

import java.util.List;
//人气推荐
public class HotBean {
    /**
     * errno : 0
     * errmsg :
     * data : {"count":24,"totalPages":3,"pageSize":10,"currentPage":1,"data":[{"id":1153006,"name":"魔兽世界 纪念版 麻将套装","list_pic_url":"http://yanxuan.nosdn.127.net/2743921b945a6c71fcdc3c5282a03413.png","retail_price":1288},{"id":1152101,"name":"魔兽世界 部落 奥格瑞玛 拉杆箱 可登机","list_pic_url":"http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png","retail_price":888},{"id":1152031,"name":"魔兽世界-伊利丹颈枕眼罩套装","list_pic_url":"http://yanxuan.nosdn.127.net/fd6e78a397bd9e9804116a36f0270b0a.png","retail_price":99},{"id":1152009,"name":"魔兽世界 联盟 护腕 一只","list_pic_url":"http://yanxuan.nosdn.127.net/ae6d41117717387b82dcaf1dfce0cd97.png","retail_price":29},{"id":1152008,"name":"魔兽世界 部落 护腕 一只","list_pic_url":"http://yanxuan.nosdn.127.net/203cb83d93606865e3ddde57b69b9e9a.png","retail_price":29},{"id":1143015,"name":"婴儿床蔺草席","list_pic_url":"http://yanxuan.nosdn.127.net/50e197854e0ada79c37b7215a1574450.png","retail_price":79},{"id":1135054,"name":"美式田园风蜻蜓提花窗帘","list_pic_url":"http://yanxuan.nosdn.127.net/30d7daa0824fbb61b6c36175c8203349.png","retail_price":559},{"id":1134036,"name":"凤梨酥 360克","list_pic_url":"http://yanxuan.nosdn.127.net/9356cc27b22bd47ad43913d13226555f.png","retail_price":38},{"id":1127052,"name":"纯棉水洗色织格夏凉被","list_pic_url":"http://yanxuan.nosdn.127.net/4f483526cfe3b953f403ae02049df5b9.png","retail_price":169},{"id":1116032,"name":"怀抱休闲椅组合","list_pic_url":"http://yanxuan.nosdn.127.net/45176a783387751fc07a07f5031dd62c.png","retail_price":3499}],"filterCategory":[{"id":0,"name":"全部","checked":true},{"id":1005000,"name":"居家","checked":false},{"id":1008000,"name":"配件","checked":false},{"id":1005002,"name":"饮食","checked":false},{"id":1019000,"name":"志趣","checked":false}],"goodsList":[{"id":1153006,"name":"魔兽世界 纪念版 麻将套装","list_pic_url":"http://yanxuan.nosdn.127.net/2743921b945a6c71fcdc3c5282a03413.png","retail_price":1288},{"id":1152101,"name":"魔兽世界 部落 奥格瑞玛 拉杆箱 可登机","list_pic_url":"http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png","retail_price":888},{"id":1152031,"name":"魔兽世界-伊利丹颈枕眼罩套装","list_pic_url":"http://yanxuan.nosdn.127.net/fd6e78a397bd9e9804116a36f0270b0a.png","retail_price":99},{"id":1152009,"name":"魔兽世界 联盟 护腕 一只","list_pic_url":"http://yanxuan.nosdn.127.net/ae6d41117717387b82dcaf1dfce0cd97.png","retail_price":29},{"id":1152008,"name":"魔兽世界 部落 护腕 一只","list_pic_url":"http://yanxuan.nosdn.127.net/203cb83d93606865e3ddde57b69b9e9a.png","retail_price":29},{"id":1143015,"name":"婴儿床蔺草席","list_pic_url":"http://yanxuan.nosdn.127.net/50e197854e0ada79c37b7215a1574450.png","retail_price":79},{"id":1135054,"name":"美式田园风蜻蜓提花窗帘","list_pic_url":"http://yanxuan.nosdn.127.net/30d7daa0824fbb61b6c36175c8203349.png","retail_price":559},{"id":1134036,"name":"凤梨酥 360克","list_pic_url":"http://yanxuan.nosdn.127.net/9356cc27b22bd47ad43913d13226555f.png","retail_price":38},{"id":1127052,"name":"纯棉水洗色织格夏凉被","list_pic_url":"http://yanxuan.nosdn.127.net/4f483526cfe3b953f403ae02049df5b9.png","retail_price":169},{"id":1116032,"name":"怀抱休闲椅组合","list_pic_url":"http://yanxuan.nosdn.127.net/45176a783387751fc07a07f5031dd62c.png","retail_price":3499}]}
     */

    private int errno;
    private String errmsg;
    private DataBeanX data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * count : 24
         * totalPages : 3
         * pageSize : 10
         * currentPage : 1
         * data : [{"id":1153006,"name":"魔兽世界 纪念版 麻将套装","list_pic_url":"http://yanxuan.nosdn.127.net/2743921b945a6c71fcdc3c5282a03413.png","retail_price":1288},{"id":1152101,"name":"魔兽世界 部落 奥格瑞玛 拉杆箱 可登机","list_pic_url":"http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png","retail_price":888},{"id":1152031,"name":"魔兽世界-伊利丹颈枕眼罩套装","list_pic_url":"http://yanxuan.nosdn.127.net/fd6e78a397bd9e9804116a36f0270b0a.png","retail_price":99},{"id":1152009,"name":"魔兽世界 联盟 护腕 一只","list_pic_url":"http://yanxuan.nosdn.127.net/ae6d41117717387b82dcaf1dfce0cd97.png","retail_price":29},{"id":1152008,"name":"魔兽世界 部落 护腕 一只","list_pic_url":"http://yanxuan.nosdn.127.net/203cb83d93606865e3ddde57b69b9e9a.png","retail_price":29},{"id":1143015,"name":"婴儿床蔺草席","list_pic_url":"http://yanxuan.nosdn.127.net/50e197854e0ada79c37b7215a1574450.png","retail_price":79},{"id":1135054,"name":"美式田园风蜻蜓提花窗帘","list_pic_url":"http://yanxuan.nosdn.127.net/30d7daa0824fbb61b6c36175c8203349.png","retail_price":559},{"id":1134036,"name":"凤梨酥 360克","list_pic_url":"http://yanxuan.nosdn.127.net/9356cc27b22bd47ad43913d13226555f.png","retail_price":38},{"id":1127052,"name":"纯棉水洗色织格夏凉被","list_pic_url":"http://yanxuan.nosdn.127.net/4f483526cfe3b953f403ae02049df5b9.png","retail_price":169},{"id":1116032,"name":"怀抱休闲椅组合","list_pic_url":"http://yanxuan.nosdn.127.net/45176a783387751fc07a07f5031dd62c.png","retail_price":3499}]
         * filterCategory : [{"id":0,"name":"全部","checked":true},{"id":1005000,"name":"居家","checked":false},{"id":1008000,"name":"配件","checked":false},{"id":1005002,"name":"饮食","checked":false},{"id":1019000,"name":"志趣","checked":false}]
         * goodsList : [{"id":1153006,"name":"魔兽世界 纪念版 麻将套装","list_pic_url":"http://yanxuan.nosdn.127.net/2743921b945a6c71fcdc3c5282a03413.png","retail_price":1288},{"id":1152101,"name":"魔兽世界 部落 奥格瑞玛 拉杆箱 可登机","list_pic_url":"http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png","retail_price":888},{"id":1152031,"name":"魔兽世界-伊利丹颈枕眼罩套装","list_pic_url":"http://yanxuan.nosdn.127.net/fd6e78a397bd9e9804116a36f0270b0a.png","retail_price":99},{"id":1152009,"name":"魔兽世界 联盟 护腕 一只","list_pic_url":"http://yanxuan.nosdn.127.net/ae6d41117717387b82dcaf1dfce0cd97.png","retail_price":29},{"id":1152008,"name":"魔兽世界 部落 护腕 一只","list_pic_url":"http://yanxuan.nosdn.127.net/203cb83d93606865e3ddde57b69b9e9a.png","retail_price":29},{"id":1143015,"name":"婴儿床蔺草席","list_pic_url":"http://yanxuan.nosdn.127.net/50e197854e0ada79c37b7215a1574450.png","retail_price":79},{"id":1135054,"name":"美式田园风蜻蜓提花窗帘","list_pic_url":"http://yanxuan.nosdn.127.net/30d7daa0824fbb61b6c36175c8203349.png","retail_price":559},{"id":1134036,"name":"凤梨酥 360克","list_pic_url":"http://yanxuan.nosdn.127.net/9356cc27b22bd47ad43913d13226555f.png","retail_price":38},{"id":1127052,"name":"纯棉水洗色织格夏凉被","list_pic_url":"http://yanxuan.nosdn.127.net/4f483526cfe3b953f403ae02049df5b9.png","retail_price":169},{"id":1116032,"name":"怀抱休闲椅组合","list_pic_url":"http://yanxuan.nosdn.127.net/45176a783387751fc07a07f5031dd62c.png","retail_price":3499}]
         */

        private int count;
        private int totalPages;
        private int pageSize;
        private int currentPage;
        private List<DataBean> data;
        private List<FilterCategoryBean> filterCategory;
        private List<GoodsListBean> goodsList;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<FilterCategoryBean> getFilterCategory() {
            return filterCategory;
        }

        public void setFilterCategory(List<FilterCategoryBean> filterCategory) {
            this.filterCategory = filterCategory;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class DataBean {
            /**
             * id : 1153006
             * name : 魔兽世界 纪念版 麻将套装
             * list_pic_url : http://yanxuan.nosdn.127.net/2743921b945a6c71fcdc3c5282a03413.png
             * retail_price : 1288
             */

            private int id;
            private String name;
            private String list_pic_url;
            private int retail_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getList_pic_url() {
                return list_pic_url;
            }

            public void setList_pic_url(String list_pic_url) {
                this.list_pic_url = list_pic_url;
            }

            public int getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(int retail_price) {
                this.retail_price = retail_price;
            }
        }

        public static class FilterCategoryBean {
            /**
             * id : 0
             * name : 全部
             * checked : true
             */

            private int id;
            private String name;
            private boolean checked;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isChecked() {
                return checked;
            }

            public void setChecked(boolean checked) {
                this.checked = checked;
            }
        }

        public static class GoodsListBean {
            /**
             * id : 1153006
             * name : 魔兽世界 纪念版 麻将套装
             * list_pic_url : http://yanxuan.nosdn.127.net/2743921b945a6c71fcdc3c5282a03413.png
             * retail_price : 1288
             */

            private int id;
            private String name;
            private String list_pic_url;
            private float retail_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getList_pic_url() {
                return list_pic_url;
            }

            public void setList_pic_url(String list_pic_url) {
                this.list_pic_url = list_pic_url;
            }

            public float getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(float retail_price) {
                this.retail_price = retail_price;
            }
        }
    }
}
