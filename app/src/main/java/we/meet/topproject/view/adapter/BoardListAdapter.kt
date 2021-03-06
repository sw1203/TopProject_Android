package we.meet.topproject.view.adapter

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_board.view.*
import we.meet.topproject.R
import we.meet.topproject.model.BoardModel
import java.sql.Date

class BoardListAdapter (private val boarditem: ArrayList<BoardModel>, val itemClick: (BoardModel)->Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return boarditem.size
    }
    fun addItem(boardModel:BoardModel){
        boarditem.add(boardModel)
    }
   inner class BoardViewHolder(view: View, itemClick: (BoardModel) -> Unit) : RecyclerView.ViewHolder(view) {
        //바인드 해줌으로써 어레이리스트 안에 있는 내용들을 연결시켜줌. 안해주면 설정했던 내용이 맞지 않음
        val title = view.boardTitleTv
        val description = view.boardSubTv
        val createdDate = view.boardDateTv
        val commentCnt = view.boardCommentTv
        val image = view.boardImg

        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(boardModel: BoardModel) {
            //여기에서 bind해준다.
            title.text = boardModel.title
            description.text = boardModel.description
            createdDate.text = boardModel.createdDate.toDateString("yyyy.MM.dd HH:mm")
            commentCnt.text = boardModel.commentCnt.toString()
//            image.setImageResource(boardModel.image)
            itemView.setOnClickListener { itemClick(boardModel) }
        }

    }//class BoardViewHolder


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_board, parent, false)
        val viewHolder = BoardViewHolder(view,itemClick)

        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val boardModel = boarditem[position]

        val boardViewHolder = holder as BoardViewHolder
        boardViewHolder.bind(boardModel)
    }


}
@RequiresApi(Build.VERSION_CODES.N)
fun Long.toDateString(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format((Date(this)))
}