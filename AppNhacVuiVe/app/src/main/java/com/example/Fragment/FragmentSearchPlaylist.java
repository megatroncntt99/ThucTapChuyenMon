package com.example.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Adapter.AllPlaylistAdapter;
import com.example.Adapter.PlaylistAdapter;
import com.example.Adapter.SearchPlaylistAdapter;
import com.example.Adapter.SongLikeAdapter;
import com.example.FunctionDesign.FunctionDesign;
import com.example.Model.Playlist;
import com.example.Model.Search;
import com.example.Model.Song;
import com.example.NetworkCheck.ConnectionReceiver;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentSearchPlaylist extends Fragment {

    View viewSearchPlaylist;
    RecyclerView recyclerViewSearchPlaylist;
    ProgressBar progressBar;
    TextView txtNoDataSearchPlaylist;

    ArrayList<Playlist> playlistArrayList;
    SearchPlaylistAdapter searchPlaylistAdapter;
    String key = "*";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewSearchPlaylist = inflater.inflate(R.layout.fragment_search_playlist, container, false);
        remap();
        return viewSearchPlaylist;
    }


    public void GetData() {

        progressBar.setVisibility(View.VISIBLE);
        DataService dataService = APIService.getService();
        Call<Search> callback = dataService.GetDataSearchSong(key);
        callback.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                Search search = response.body();
                playlistArrayList = (ArrayList<Playlist>) search.getPlaylist();
                if (playlistArrayList.size() > 0) {
                    txtNoDataSearchPlaylist.setVisibility(View.GONE);

                    searchPlaylistAdapter = new SearchPlaylistAdapter(getActivity(), R.layout.row_item_search_playlist, playlistArrayList);
                    recyclerViewSearchPlaylist.setAdapter(searchPlaylistAdapter);
                } else {
                    searchPlaylistAdapter = new SearchPlaylistAdapter(getActivity(), R.layout.row_item_search_playlist, playlistArrayList);
                    recyclerViewSearchPlaylist.setAdapter(searchPlaylistAdapter);
                    txtNoDataSearchPlaylist.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }


    public void GetDataSearchPlaylist(String keySearch) {
        if (ConnectionReceiver.isConnected()) {
            key = keySearch;
        } else {
            progressBar.setVisibility(View.VISIBLE);
            txtNoDataSearchPlaylist.setVisibility(View.GONE);
        }
    }

    private void remap() {
        progressBar = viewSearchPlaylist.findViewById(R.id.progressBarSearchPlaylist);
        recyclerViewSearchPlaylist = viewSearchPlaylist.findViewById(R.id.recyclerViewSearchPlaylist);
        txtNoDataSearchPlaylist = viewSearchPlaylist.findViewById(R.id.txtNoDataSearchPlaylist);
        playlistArrayList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewSearchPlaylist.setLayoutManager(layoutManager);
    }
}
