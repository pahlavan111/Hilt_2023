package com.bp.hilt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@AndroidEntryPoint
class ModuleActivity : AppCompatActivity() {

    @Inject
    lateinit var complexModel: ComplexModel

    @Inject
    lateinit var a: A

    @Inject
    lateinit var s3: SomeClass3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module)

        Toast.makeText(this, a.s1.s2.text, Toast.LENGTH_SHORT).show()

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {

            Toast.makeText(baseContext, "text is: ${complexModel.text()}", Toast.LENGTH_SHORT)
                .show()
        }


    }
}

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @ActivityScoped
    @Provides
    fun provideComplexModel(): ComplexModel {
        return ComplexModel()
    }
}

class ComplexModel {
    fun text(): String = "blaw blaw"
}

///////////////////////
class A @Inject constructor() {

    @Inject
    lateinit var s1: SomeClass1
}

class SomeClass1 @Inject constructor(var s2: SomeClass2)

class SomeClass2 @Inject constructor() {
    val text = "s2 text"
}

class SomeClass3 @Inject constructor() {
    val text = "s3 text"
}