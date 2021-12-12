package com.example.interactivemap.ui.screens

import android.content.res.Resources
import android.graphics.pdf.PdfDocument
import android.widget.ImageView
import androidx.compose.ui.Modifier
import com.example.interactivemap.viewmodels.*
import com.example.interactivemap.*
import ovh.plrapps.mapcompose.ui.MapUI
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.interactivemap.ui.theme.VSTUBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.onMarkerClick
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.interactivemap.R
import com.example.interactivemap.ui.theme.InteractiveMapTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.util.*


var sizeSpaceBetweenButtons: Float = 1.5F
var images : List<Int> = listOf()


@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    val floors = listOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    val selectedOption = remember { mutableStateOf(floors[0]) }
    val centerOn = remember { mutableStateOf("") }
    val r = remember { mutableStateOf("") }
    val imgNumb = remember { mutableStateOf(-1) }
    val isDark = remember { mutableStateOf(false) }
    InteractiveMapTheme(darkTheme = isDark.value) {
        Surface(color = MaterialTheme.colors.background) {
            if (selectedOption.value == 9 && isDark.value) {
                if(!r.value.startsWith("9")) {
                    r.value = ""
                }
                val floor: NinthFloorDark = viewModel()
                MapUI(modifier, state = floor.state)
                floor.state.onMarkerClick { id, x, y ->
                    if (r.value == id) {
                        r.value = ""
                    } else {
                        r.value = id
                    }
                }
                if (!centerOn.value.isEmpty()) {
                    floor.onCenter(centerOn.value)
                    centerOn.value = ""
                }
            }
            else if (selectedOption.value == 9 && !isDark.value) {
                if(!r.value.startsWith("9")) {
                    r.value = ""
                }
                val floor: NinthFloor = viewModel()
                MapUI(modifier, state = floor.state, )
                floor.state.onMarkerClick { id, x, y ->
                    if (r.value == id) {
                        r.value = ""
                    } else {
                        r.value = id
                    }
                }
                if (!centerOn.value.isEmpty()) {
                    floor.onCenter(centerOn.value)
                    centerOn.value = ""
                }
            }
            if (selectedOption.value == 8 && isDark.value) {
                if(!r.value.startsWith("8")) {
                    r.value = ""
                }
                val floor: EighthFloorDark = viewModel()
                MapUI(modifier, state = floor.state)
                floor.state.onMarkerClick { id, x, y ->
                    if (r.value == id) {
                        r.value = ""
                    } else {
                        r.value = id
                    }
                }
                if (!centerOn.value.isEmpty()) {
                    floor.onCenter(centerOn.value)
                    centerOn.value = ""
                }
            }
            else if (selectedOption.value == 8 && !isDark.value) {
                if(!r.value.startsWith("8")) {
                    r.value = ""
                }
                val floor: EighthFloor = viewModel()
                MapUI(modifier, state = floor.state)
                floor.state.onMarkerClick { id, x, y ->
                    if (r.value == id) {
                        r.value = ""
                    } else {
                        r.value = id
                    }
                }
                if (!centerOn.value.isEmpty()) {
                    floor.onCenter(centerOn.value)
                    centerOn.value = ""
                }
            }
            if (selectedOption.value == 6 && isDark.value) {
                if(!r.value.startsWith("6")) {
                    r.value = ""
                }
                val floor: SixthFloorDark = viewModel()
                MapUI(modifier, state = floor.state)
                floor.state.onMarkerClick { id, x, y ->
                    if (r.value == id) {
                        r.value = ""
                    } else {
                        r.value = id
                    }
                }
                if (!centerOn.value.isEmpty()) {
                    floor.onCenter(centerOn.value)
                    centerOn.value = ""
                }
            }
            else if (selectedOption.value == 6 && !isDark.value) {
                if(!r.value.startsWith("6")) {
                    r.value = ""
                }
                val floor: SixthFloor = viewModel()
                MapUI(modifier, state = floor.state)
                floor.state.onMarkerClick { id, x, y ->
                    if (r.value == id) {
                        r.value = ""
                    } else {
                        r.value = id
                    }
                }
                if (!centerOn.value.isEmpty()) {
                    floor.onCenter(centerOn.value)
                    centerOn.value = ""
                }
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Spacer(modifier = Modifier.height(70.dp))
                floors.forEach { floor ->
                    val selected = selectedOption.value == floor
                    Button(onClick = { selectedOption.value = floor }, modifier =
                    Modifier
                        .selectable(
                            selected = selected,
                            onClick = { }
                        ),
                        border = BorderStroke(
                            width = if (selected) {
                                2.dp
                            } else {
                                0.dp
                            }, color = MaterialTheme.colors.secondary
                        ),
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = if (selected) {
                                MaterialTheme.colors.background
                            } else {
                                MaterialTheme.colors.primary
                            },
                            contentColor = if(selected) MaterialTheme.colors.primaryVariant else Color.Black
                        )
                    ) {
                        Text(text = floor.toString())
                    }
                    Spacer(modifier = Modifier.height(sizeSpaceBetweenButtons.dp))
                }
            }

            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Button(onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message = "Данная функция временно не поддерживается")
                    }
                }
                )
                { Text("Навигатор", color = Color.Black) }

                Spacer(modifier = Modifier.height(sizeSpaceBetweenButtons.dp))
            }

            cabinetDescription(r, imgNumb)

            val mainViewModel: HomeViewModel = viewModel()
            mainViewModel.cabinetList = createComponentTutorialList()

            HomeScreen(
                viewModel = mainViewModel,
                selectedOption = selectedOption,
                onCenter = centerOn,
                isDark = isDark
            )

            fullsizeImage(imgNumb)
        }
    }
}

data class SuggestionModel(val tag: String) {
    val id = tag.hashCode()
}

val suggestionList = listOf(
    SuggestionModel("Преподавательская"),
    SuggestionModel("Лаборатория"),
    SuggestionModel("Практика"),
    SuggestionModel("V.I.S.D.O.M."),
    SuggestionModel("Лекционная"),
    SuggestionModel("9 этаж"),
    SuggestionModel("8 этаж"),
    SuggestionModel("6 этаж"),
)

data class CabinetSectionModel(
    val title: String,
    val action: @Composable (() -> Unit)? = null,
    val description: String,
    val tags: List<String> = listOf(),
    val tagColor: Color = Color(0xff00BCD4),
    var expanded: Boolean = false
)

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun createComponentTutorialList(): List<CabinetSectionModel> {
    val cab601 = CabinetSectionModel(
        title = "601",
        description = cabinetToDiscription("601"),
        tags = listOf(
            "6 этаж",
            "Практика"
        )
    )

    val cab602 = CabinetSectionModel(
        title = "602",
        description = cabinetToDiscription("602"),
        tags = listOf(
            "6 этаж",
            "Практика"
        )
    )

    val cab603 = CabinetSectionModel(
        title = "603",
        description = cabinetToDiscription("603"),
        tags = listOf(
            "6 этаж",
            "Практика"
        )
    )

    val cab604 = CabinetSectionModel(
        title = "604",
        description = cabinetToDiscription("604"),
        tags = listOf(
            "6 этаж",
            "Лаборатория"
        )
    )

    val cab605 = CabinetSectionModel(
        title = "605",
        description = cabinetToDiscription("605"),
        tags = listOf(
            "6 этаж",
            "Лаборатория"
        )
    )

    val cab606 = CabinetSectionModel(
        title = "606",
        description = cabinetToDiscription("606"),
        tags = listOf(
            "6 этаж",
            "Преподавательская"
        )
    )

    val cab607 = CabinetSectionModel(
        title = "607",
        description = cabinetToDiscription("607"),
        tags = listOf(
            "6 этаж",
            "Преподавательская"
        )
    )

    val cab608 = CabinetSectionModel(
        title = "608",
        description = cabinetToDiscription("608"),
        tags = listOf(
            "6 этаж",
            "Преподавательская"
        )
    )

    val cab609 = CabinetSectionModel(
        title = "609",
        description = cabinetToDiscription("609"),
        tags = listOf(
            "6 этаж",
            "Преподавательская"
        )
    )

    val cab610 = CabinetSectionModel(
        title = "610",
        description = cabinetToDiscription("610"),
        tags = listOf(
            "6 этаж",
            "Практика"
        )
    )

    val cab611 = CabinetSectionModel(
        title = "611",
        description = cabinetToDiscription("611"),
        tags = listOf(
            "6 этаж",
            "Практика"
        )
    )

    val cab612 = CabinetSectionModel(
        title = "612",
        description = cabinetToDiscription("612"),
        tags = listOf(
            "6 этаж",
            "Лаборатория"
        )
    )

    val cab801 = CabinetSectionModel(
        title = "801",
        description = cabinetToDiscription("801"),
        tags = listOf(
            "8 этаж",
            "Практика"
        )
    )

    val cab802 = CabinetSectionModel(
        title = "802",
        description = cabinetToDiscription("802"),
        tags = listOf(
            "8 этаж",
            "Лекционная"
        )
    )

    val cab803 = CabinetSectionModel(
        title = "803",
        description = cabinetToDiscription("803"),
        tags = listOf(
            "8 этаж",
            "Преподавательская"
        )
    )

    val cab804 = CabinetSectionModel(
        title = "804",
        description = cabinetToDiscription("804"),
        tags = listOf(
            "8 этаж",
            "Практика"
        )
    )

    val cab805 = CabinetSectionModel(
        title = "805",
        description = cabinetToDiscription("805"),
        tags = listOf(
            "8 этаж",
            "Практика"
        )
    )

    val cab806 = CabinetSectionModel(
        title = "806",
        description = cabinetToDiscription("806"),
        tags = listOf(
            "8 этаж",
            "Практика"
        )
    )

    val cab807 = CabinetSectionModel(
        title = "807",
        description = cabinetToDiscription("807"),
        tags = listOf(
            "8 этаж",
            "Преподавательская"
        )
    )

    val cab901 = CabinetSectionModel(
        title = "901",
        description = cabinetToDiscription("901"),
        tags = listOf(
            "9 этаж",
            "Лекционная"
        )
    )

    val cab902a = CabinetSectionModel(
        title = "902а",
        description = cabinetToDiscription("902а"),
        tags = listOf(
            "9 этаж",
            "Лаборатория"
        )
    )

    val cab902b = CabinetSectionModel(
        title = "902б",
        description = cabinetToDiscription("902б"),
        tags = listOf(
            "9 этаж",
            "Лаборатория"
        )
    )

    val cab902v = CabinetSectionModel(
        title = "902в",
        description = cabinetToDiscription("902в"),
        tags = listOf(
            "9 этаж",
            "Лаборатория",
            "Практика"
        )
    )

    val cab903 = CabinetSectionModel(
        title = "903",
        description = cabinetToDiscription("903"),
        tags = listOf(
            "9 этаж",
            "V.I.S.D.O.M."
        )
    )

    val cab904 = CabinetSectionModel(
        title = "904",
        description = cabinetToDiscription("904"),
        tags = listOf(
            "9 этаж",
            "Преподавательская"
        )
    )

    val cab905 = CabinetSectionModel(
        title = "905",
        description = cabinetToDiscription("905"),
        tags = listOf(
            "9 этаж",
            "Преподавательская"
        )
    )

    val cab906 = CabinetSectionModel(
        title = "906",
        description = cabinetToDiscription("906"),
        tags = listOf(
            "9 этаж",
            "Практика"
        )
    )

    val cab907 = CabinetSectionModel(
        title = "907",
        description = cabinetToDiscription("907"),
        tags = listOf(
            "9 этаж",
            "Практика"
        )
    )

    val cab908 = CabinetSectionModel(
        title = "908",
        description = cabinetToDiscription("908"),
        tags = listOf(
            "9 этаж",
            "Практика"
        )
    )

    return listOf(
        cab601,
        cab602,
        cab603,
        cab604,
        cab605,
        cab606,
        cab607,
        cab608,
        cab609,
        cab610,
        cab611,
        cab612,

        cab801,
        cab802,
        cab803,
        cab804,
        cab805,
        cab806,
        cab807,

        cab901,
        cab902a,
        cab902b,
        cab902v,
        cab903,
        cab904,
        cab905,
        cab906,
        cab907,
        cab908
    )
}

class HomeViewModel : ViewModel() {

    var selectedPage: Int = 0

    var cabinetList = emptyList<CabinetSectionModel>()

    private val _suggestionState = MutableStateFlow<List<SuggestionModel>>(suggestionList)

    val suggestionState: SharedFlow<List<SuggestionModel>>
        get() = _suggestionState


    fun addSuggestion(suggestionModel: SuggestionModel) {

    }

    fun getCabinets(query: String): List<CabinetSectionModel> {

        val filteredList = linkedSetOf<CabinetSectionModel>()

        cabinetList.forEach { cabinetSectionModel ->

            if (cabinetSectionModel.title.contains(query, ignoreCase = true)) {
                filteredList.add(cabinetSectionModel)
            }

            cabinetSectionModel.tags.forEach {
                if (it.contains(query, ignoreCase = true)) {
                    filteredList.add(cabinetSectionModel)
                }
            }
        }

        return if (query.isEmpty()) cabinetList else filteredList.toList()
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    state: SearchState<CabinetSectionModel, SuggestionModel> = rememberSearchState(),
    selectedOption: MutableState<Int>,
    onCenter: MutableState<String>,
    isDark: MutableState<Boolean>
) {

    state.suggestions = viewModel.suggestionState.collectAsState(initial = suggestionList).value

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        SearchBar(
            query = state.query,
            onQueryChange = { state.query = it },
            onSearchFocusChange = { state.focused = it },
            onClearQuery = { state.query = TextFieldValue("") },
            onBack = { state.query = TextFieldValue("") },
            searching = state.searching,
            focused = state.focused,
            modifier = modifier,
            isDark = isDark
        )

        LaunchedEffect(state.query.text) {
            state.searching = true
            delay(100)
            state.searchResults = viewModel.getCabinets(state.query.text)
            state.searching = false
        }

        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        when (state.searchDisplay) {
            SearchDisplay.InitialResults -> {
            }

            SearchDisplay.NoResults -> {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = MaterialTheme.colors.background,
                        border = BorderStroke(width = 3.5.dp, color = MaterialTheme.colors.primary),
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No Results!", fontSize = 24.sp, color = Color(0xffDD2C00))
                        }
                    }
                }
            }

            SearchDisplay.Suggestions -> {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = MaterialTheme.colors.background,
                        border = BorderStroke(width = 3.5.dp, color = MaterialTheme.colors.primary),
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        val scrollState = rememberScrollState()
                        LaunchedEffect(Unit) { scrollState.animateScrollTo(0) }
                        Column(modifier = Modifier
                            .padding(10.dp)
                            .verticalScroll(scrollState)) {
                            viewModel.cabinetList.forEach { cab ->
                                Box(
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Card(shape = RoundedCornerShape(10.dp),
                                        backgroundColor = MaterialTheme.colors.background,
                                        border = BorderStroke(width = 1.5.dp, color = MaterialTheme.colors.primary),
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        onClick = {
                                            selectedOption.value = cab.title.get(0).digitToInt()
                                            onCenter.value = cab.title
                                            focusManager.clearFocus()
                                            keyboardController?.hide()
                                            state.query = TextFieldValue("")
                                        }
                                    ) {
                                        Column(Modifier.padding(5.dp)) {
                                            Text(text = "Аудитория " + cab.title,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold)
                                            Text(text = cab.description,
                                                fontSize = 15.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            SearchDisplay.Results -> {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = MaterialTheme.colors.background,
                        border = BorderStroke(width = 3.5.dp, color = MaterialTheme.colors.primary),
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        val scrollState = rememberScrollState()
                        LaunchedEffect(Unit) { scrollState.animateScrollTo(0) }
                        Column(modifier = Modifier
                            .padding(10.dp)
                            .verticalScroll(scrollState)) {
                            state.searchResults.forEach { res ->
                                Box(
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Card(shape = RoundedCornerShape(10.dp),
                                        backgroundColor = MaterialTheme.colors.background,
                                        border = BorderStroke(width = 1.5.dp, color = MaterialTheme.colors.primary),
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        onClick = {
                                            selectedOption.value = res.title.get(0).digitToInt()
                                            onCenter.value = res.title
                                            focusManager.clearFocus()
                                            keyboardController?.hide()
                                            state.query = TextFieldValue("")
                                        }
                                    ) {
                                        Column(Modifier.padding(5.dp)) {
                                            Text(text = "Аудитория " + res.title,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold)
                                            Text(text = res.description,
                                                fontSize = 15.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@ExperimentalAnimationApi
fun cabinetDescription(idCabindet: MutableState<String>, numbImg: MutableState<Int>) {
    if (!idCabindet.value.isEmpty()) {
        var visibleDescription = remember { mutableStateOf(true) }
        AnimatedVisibility(visible = visibleDescription.value) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 80.dp, end = 2.dp, bottom = 2.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Card(
                    shape = RoundedCornerShape(15.dp),
                    backgroundColor = MaterialTheme.colors.background,
                    border = BorderStroke(width = 3.5.dp, color = MaterialTheme.colors.primary),
                    modifier = Modifier
                        .size(310.dp, 240.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = idCabindet.value,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, top = 60.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(cabinetToDiscription(idCabindet.value))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        imagesCabinet(numbImg, idCabindet.value)
                    }

                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top
                    ) {
                        IconButton(
                            modifier = Modifier.padding(start = 2.dp),
                            onClick = {
                                visibleDescription.value = false
                                idCabindet.value = ""
                            }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun imagesCabinet(numbImg: MutableState<Int>, cabinet: String) {
    images = cabinetToImages(cabinet)
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) { scrollState.animateScrollTo(0) }
    Row(modifier = Modifier
        .padding(10.dp)
        .horizontalScroll(scrollState)) {
        images.forEach { img ->
            Image(painter = painterResource(id = img), contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clickable { numbImg.value = images.indexOf(img) })
        }
    }
}

@ExperimentalPagerApi
@Composable
fun fullsizeImage(numbImg: MutableState<Int>) {
    if(numbImg.value != -1) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)) {
//            var scale by remember { mutableStateOf(1f) }
//            var offset by remember { mutableStateOf(Offset.Zero) }
//            val state = rememberTransformableState { zoomChange, offsetChange, _ ->
//                scale *= zoomChange
//                offset += offsetChange
//            }
            val pagerState = rememberPagerState(numbImg.value)

            HorizontalPager(count = images.size, state = pagerState) { page ->
                Image(
                    painter = painterResource(id = images.get(page)), contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
//                        .graphicsLayer(
//                            scaleX = scale,
//                            scaleY = scale,
//                            translationX = offset.x,
//                            translationY = offset.y
//                        )
//                        .transformable(state = state)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.fillMaxWidth())
            IconButton(
                modifier = Modifier.padding(end = 2.dp),
                onClick = {
                    numbImg.value = -1
                }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = Color.White)
            }
        }
    }
}

fun cabinetToDiscription(cabinet: String): String {
    if (cabinet == "901") return "Лекционный класс"
    else if (cabinet == "902а") return "Аудитория для проведения лабораторных работ"
    else if (cabinet == "902б") return "Аудитория для проведения лабораторных работ"
    else if (cabinet == "902в") return "Аудитория для проведения лабораторных работ и практик"
    else if (cabinet == "903") return "Аудитория V.I.S.D.O.M. laboratory"
    else if (cabinet == "904") return "Преподавательская"
    else if (cabinet == "905") return "Преподавательская"
    else if (cabinet == "906") return "Аудитория для проведения практик"
    else if (cabinet == "907") return "Аудитория для проведения практик"
    else if (cabinet == "908") return "Аудитория для проведения практик"

    else if (cabinet == "801") return "Аудитория для проведения практик"
    else if (cabinet == "802") return "Лекционный класс"
    else if (cabinet == "803") return "Преподавательская"
    else if (cabinet == "804") return "Аудитория для проведения практик"
    else if (cabinet == "805") return "Аудитория для проведения практик"
    else if (cabinet == "806") return "Аудитория для проведения практик"
    else if (cabinet == "807") return "Преподавательская"

    else if (cabinet == "601") return "Аудитория для проведения практик"
    else if (cabinet == "602") return "Аудитория для проведения практик"
    else if (cabinet == "603") return "Аудитория для проведения практик"
    else if (cabinet == "604") return "Аудитория для проведения лабораторных работ"
    else if (cabinet == "605") return "Аудитория для проведения лабораторных работ"
    else if (cabinet == "606") return "Преподавательская"
    else if (cabinet == "607") return "Преподавательская"
    else if (cabinet == "608") return "Преподавательская"
    else if (cabinet == "609") return "Преподавательская"
    else if (cabinet == "610") return "Аудитория для проведения практик"
    else if (cabinet == "611") return "Аудитория для проведения практик"
    else if (cabinet == "612") return "Аудитория для проведения лабораторных работ"
    else return ""
}

var imagesCab602 = listOf(R.drawable.cab602img1, R.drawable.cab602img2, R.drawable.cab602img3, R.drawable.cab602img4)
var imagesCab604 = listOf(R.drawable.cab604img1, R.drawable.cab604img2, R.drawable.cab604img3, R.drawable.cab604img4)
var imagesCab606 = listOf(R.drawable.cab606img1, R.drawable.cab606img2, R.drawable.cab606img3, R.drawable.cab606img4)
var imagesCab901 = listOf(R.drawable.cab901img1, R.drawable.cab901img2, R.drawable.cab901img3, R.drawable.cab901img4, R.drawable.cab901img5)
var imagesCab902a = listOf(R.drawable.cab902aimg1, R.drawable.cab902aimg2)
var imagesCab902b = listOf(R.drawable.cab902bimg1, R.drawable.cab902bimg2)
var imagesCab903 = listOf(R.drawable.cab903img1, R.drawable.cab903img2, R.drawable.cab903img3, R.drawable.cab903img4)

var stareges = listOf(R.drawable.test, R.drawable.test, R.drawable.test ,R.drawable.test, R.drawable.test, R.drawable.test, R.drawable.test)

fun cabinetToImages(cabinet: String) : List<Int> {
    if (cabinet == "602") return imagesCab602
    else if(cabinet == "604") return imagesCab604
    else if(cabinet == "606") return imagesCab606
    else if(cabinet == "901") return imagesCab901
    else if(cabinet == "902а") return imagesCab902a
    else if(cabinet == "902б") return imagesCab902b
    else if(cabinet == "903") return imagesCab903
    else return stareges
}
