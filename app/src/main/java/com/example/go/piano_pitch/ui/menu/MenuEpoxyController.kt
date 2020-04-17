package com.example.go.piano_pitch.ui.menu

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.example.go.piano_pitch.ItemPitchTypeBindingModel_
import com.example.go.piano_pitch.logic.PitchType
import javax.inject.Inject

class MenuEpoxyController @Inject constructor(
    private val listener: OnClickPitchTypeListener
) : EpoxyController() {

    @ExperimentalStdlibApi
    private val data: Array<PitchType> = PitchType.values()

    @ExperimentalStdlibApi
    override fun buildModels() {
        carousel {
            id("pitchTypes")
            numViewsToShowOnScreen(3f)
            hasFixedSize(true)

            models(
                data.mapIndexed { index, it ->
                    ItemPitchTypeBindingModel_()
                        .id(index)
                        .pitchType(it)
                        .onBind { _, view, _ ->
                            view.dataBinding.root.setOnClickListener { _ ->
                                listener.onClick(it)
                            }
                        }
                }
            )
        }
    }

    interface OnClickPitchTypeListener {
        @ExperimentalStdlibApi
        fun onClick(pitchType: PitchType)
    }
}