# TabBar
实现TabHost选项卡的功能，在布局文件中提供显示数据，设置一个监听事件就可完成与Fragment等的配合使用

TabBar是顶部选项条，类似于TabHost。
使用：
  - 拷贝values文件夹下的attrs文件以及TabBar.java即可。
  - 在布局文件中通过制定normalColor属性指明选项未被选中时的显示颜色，selectedColor属性指明选项被选中时的显示颜色。contentEntries属性指明所要显示的文本数组，在values文件夹下指定。
  - 选项卡都可以选中，TabBar通过指定OnTabSelectedListener接口实现事件回调，其中TabBar内部已经做了显示颜色的变化，事件中只需处理具体事件。可以通过View.getTag获得View的索引，从0开始。
例子：点击Tab切换Fragment的功能
  布局文件：  
  ```
  <com.example.xingfeng.tabbar.view.TabBar
        android:id="@+id/tabBar"
        android:background="#555"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        app:contentEntries="@array/tabs"
        app:normalColor="#929293"
        app:selectedColor="#B97A28"
        ></com.example.xingfeng.tabbar.view.TabBar>
 ```
 设置监听：  
 ```
     mTabBar.setOnTabSelectedListener(new TabBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(View v) {

                FragmentManager manager=getFragmentManager();
                FragmentTransaction myTransaction=manager.beginTransaction();
                currentFragment=fragments[(Integer) v.getTag()];
                myTransaction.replace(R.id.content,currentFragment);
                myTransaction.commit();


            }
        });
 ```
