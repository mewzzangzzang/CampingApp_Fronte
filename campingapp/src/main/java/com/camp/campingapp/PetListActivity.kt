package com.camp.campingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityPetListBinding
import com.camp.campingapp.model.PetList
import com.camp.campingapp.recycler.PetListAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Response

class PetListActivity : AppCompatActivity() {

    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var bottomSheetView: View
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var contentTextView: TextView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    lateinit var binding: ActivityPetListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPetListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.setOnClickListener {
        }
        getPetList("1","강릉시")

        // 바텀시트
        val showBottomSheetButton: Button = findViewById(R.id.btn_show_bottom_sheet)
        showBottomSheetButton.setOnClickListener {
            showBottomSheetDialog()
        }

    }

    private fun getPetList(donm: String,sigunguNm:String) {

        var donm: String = donm
        var sigunguNm: String = sigunguNm
        val networkService = (applicationContext as MyApplication).networkService
        val userListCall =
            networkService.petList(donm,sigunguNm)

        userListCall.enqueue(object : retrofit2.Callback<List<PetList>> {
            override fun onResponse(
                call: retrofit2.Call<List<PetList>>,
                response: Response<List<PetList>>
            ) {
                val petList = response.body()

                binding.recyclerView.adapter =
                    PetListAdapter(this@PetListActivity, petList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@PetListActivity, LinearLayoutManager.VERTICAL)
                )

            }

            override fun onFailure(call: retrofit2.Call<List<PetList>>, t: Throwable) {
                call.cancel()
            }
        })
    }

    private fun showBottomSheetDialog() {
        bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        bottomSheetDialog = object : BottomSheetDialog(this){
            override fun onStart() {
                super.onStart()
                // 바텀 시트의 슬라이딩을 잠근다
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        bottomSheetDialog.setContentView(bottomSheetView)

        button1 = bottomSheetView.findViewById(R.id.btn_option1)
        button2 = bottomSheetView.findViewById(R.id.btn_option2)
        button3 = bottomSheetView.findViewById(R.id.btn_option3)
        contentTextView = bottomSheetView.findViewById(R.id.tv_content)

        bottomSheetDialog.setOnShowListener {
            val sheetInternal = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheetBehavior = BottomSheetBehavior.from(sheetInternal!!)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            bottomSheetBehavior.isDraggable = false // 드래그로 바텀 시트를 올릴 수 없도록 설정
        }

        button1.setOnClickListener {
            updateBottomSheetLayout(R.layout.bottom_sheet_layout_option1)
        }


        button2.setOnClickListener {
            updateBottomSheetLayout(R.layout.bottom_sheet_layout_option2)
        }

        button3.setOnClickListener {
            updateBottomSheetLayout(R.layout.bottom_sheet_layout_option3)
        }

        bottomSheetDialog.show()
    }

    private fun updateBottomSheetLayout(layoutResId: Int) {

        // 바뀐 레이아웃에 포함된 뷰들 다시 초기화
        button1 = bottomSheetView.findViewById(R.id.btn_option1)
        button2 = bottomSheetView.findViewById(R.id.btn_option2)
        button3 = bottomSheetView.findViewById(R.id.btn_option3)
        contentTextView = bottomSheetView.findViewById(R.id.tv_content)
    }


}