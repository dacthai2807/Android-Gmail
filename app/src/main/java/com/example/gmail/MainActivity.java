package com.example.gmail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.example.gmail.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listViewMenu;

    ArrayList <ItemMenu> arrayList;
    MenuAdapter menuAdapter;

    private SearchView searchView;

    ListView listViewUser;

    private RecyclerView recyclerUsers;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerUsers = findViewById(R.id.recyclerUser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerUsers.setLayoutManager(linearLayoutManager);

        userAdapter = new UserAdapter(getListUsers());
        recyclerUsers.setAdapter(userAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerUsers.addItemDecoration(itemDecoration);

        mapping();
        actionToolBar();
        actionMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    private List<User> getListUsers() {
        int[] imageId = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,
                         R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,
                         R.drawable.o,R.drawable.p,R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t,R.drawable.u,
                         R.drawable.v,R.drawable.w,R.drawable.x,R.drawable.y,R.drawable.z, R.drawable.avatar};
        String[] name = {"Author","Codeforces","Topcoder Team","Dribbble","HackerEarth","CodeChef","DeviantArt","AtCoder","P.A.I.M.O.N"};
        String[] lastMessage = {"Nguyen Dac Thai","Educational Codeforces Round 130 (Rated for Div. 2)","Rapid Development Match 7 - Topcoder RDM 7 submission phase is scheduled to start at 08:00 UTC-4 on June 10th, 2022",
                                "Get more eyes on your portfolio - 3 ways to boost traffic to your design portfolio","Hiring Challenges, Hackathons & Coding Contests", "May Long Challenge II Starts Tomorrow 3 PM IST | Sponsored by Edology",
                                "DeviantArt Digest: Pride month, new badges, and more!","AtCoder Beginner Contest 254 Announcement","Genshin Impact | Yelan coming soon! Join the web event to get primogems and other rewards!"};
        String[] lastmsgTime = {"01:30 pm","12:34 pm","7:34 pm","6:32 am","5:76 am", "5:00 am","7:34 pm","2:32 am","7:76 am"};

        List <User> userArrayList = new ArrayList<>();

        for(int i = 0; i < name.length; i++) {
            int id = 26;
            if (name[i].charAt(0) >= 'A' && name[i].charAt(0) <= 'Z') id = name[i].charAt(0) - 'A';
            if (name[i].charAt(0) >= 'a' && name[i].charAt(0) <= 'z') id = name[i].charAt(0) - 'a';

            User user = new User(name[i],lastMessage[i],lastmsgTime[i],imageId[id]);
            userArrayList.add(user);
        }

        return userArrayList;
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Inbox");
    }

    private void mapping() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        listViewMenu = (ListView) findViewById(R.id.listViewMenu);
    }

    private void actionMenu() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemMenu("Inbox", R.drawable.icon_inbox));
        arrayList.add(new ItemMenu("Sent", R.drawable.icon_sent));
        arrayList.add(new ItemMenu("Snoozed", R.drawable.icon_snoozed));
        arrayList.add(new ItemMenu("Important", R.drawable.icon_important));
        menuAdapter = new MenuAdapter(this, R.layout.row_item, arrayList);
        listViewMenu.setAdapter(menuAdapter);
    }

    public void composeOnClick(View view) {

    }
}