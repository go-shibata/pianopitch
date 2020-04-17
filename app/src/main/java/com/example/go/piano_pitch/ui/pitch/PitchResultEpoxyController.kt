package com.example.go.piano_pitch.ui.pitch

import com.airbnb.epoxy.EpoxyController
import com.example.go.piano_pitch.data.Result
import com.example.go.piano_pitch.itemResult
import com.example.go.piano_pitch.ui.view.note.NotesListView
import kotlinx.android.synthetic.main.item_result.view.*
import javax.inject.Inject

class PitchResultEpoxyController @Inject constructor(
    private val onClickPlayButtonListener: OnClickPlayButtonListener
) : EpoxyController() {

    private var data: List<Result> = emptyList()

    fun setData(data: List<Result>) {
        this.data = data
        requestModelBuild()
    }

    override fun buildModels() {
        data.forEachIndexed { index, it ->
            itemResult {
                id(it.id)
                questionNumber(index + 1)
                res(it)
                listener(onClickPlayButtonListener)

                onBind { _, view, _ ->
                    val root = view.dataBinding.root
                    root.question_notes.removeAllViews()
                    it.questionNotes.forEach { list ->
                        val listView = NotesListView(root.context, list)
                        root.question_notes.addView(listView)
                    }
                    root.played_notes.removeAllViews()
                    it.playedNotes.forEach { list ->
                        val listView = NotesListView(root.context, list)
                        root.played_notes.addView(listView)
                    }
                }
            }
        }
    }

    interface OnClickPlayButtonListener {
        fun onClickPlayButton(result: Result)
    }
}