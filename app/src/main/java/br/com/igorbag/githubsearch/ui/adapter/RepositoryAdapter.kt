package br.com.igorbag.githubsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.domain.Repository

class RepositoryAdapter(private val repositories: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    var carItemLister: (Repository) -> Unit = {}
    var btnShareLister: (Repository) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository, btnShareLister, carItemLister)
    }

    override fun getItemCount(): Int = repositories.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeRepositorio: TextView = view.findViewById(R.id.tv_repository_name)
        val compartilharRepositorio: ImageView = view.findViewById(R.id.iv_share)

        fun bind(
            repository: Repository,
            shareClickListener: (Repository) -> Unit,
            itemClickListener: (Repository) -> Unit
        ) {
            nomeRepositorio.text = repository.name
            compartilharRepositorio.setOnClickListener {
                shareClickListener(repository)
            }
            itemView.setOnClickListener {
                itemClickListener(repository)
            }
        }
    }
}
