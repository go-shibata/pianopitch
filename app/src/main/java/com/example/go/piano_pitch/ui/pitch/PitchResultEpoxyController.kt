package com.example.go.piano_pitch.ui.pitch

import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyController
import com.example.go.piano_pitch.R
import com.example.go.piano_pitch.data.Result
import com.example.go.piano_pitch.itemResult
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
                    val layoutParams = LinearLayout.LayoutParams(
                        root.resources.getDimensionPixelSize(R.dimen.pitch_notes_size),
                        root.resources.getDimensionPixelSize(R.dimen.pitch_notes_size)
                    )
                    root.question_notes.removeAllViews()
                    it.questionNotes.forEach {
                        val textView = TextView(root.context).apply {
                            setLayoutParams(layoutParams)
                            gravity = Gravity.CENTER
                            text = it.name
                            setTextSize(
                                TypedValue.COMPLEX_UNIT_PX,
                                resources.getDimension(R.dimen.pitch_notes_text_size)
                            )
                        }
                        root.question_notes.addView(textView)
                    }
                    root.played_notes.removeAllViews()
                    it.playedNotes.forEach {
                        val textView = TextView(root.context).apply {
                            setLayoutParams(layoutParams)
                            gravity = Gravity.CENTER
                            text = it.name
                            setTextSize(
                                TypedValue.COMPLEX_UNIT_PX,
                                resources.getDimension(R.dimen.pitch_notes_text_size)
                            )
                        }
                        root.played_notes.addView(textView)
                    }
                }
            }
        }
    }

    interface OnClickPlayButtonListener {
        fun onClickPlayButton(result: Result)
    }
}