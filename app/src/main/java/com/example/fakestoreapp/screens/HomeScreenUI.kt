package com.example.fakestoreapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.fakestoreapp.R
import com.example.fakestoreapp.response.Product
import com.example.fakestoreapp.viewModel.HomeViewModel

@Preview(showBackground = true)
@Composable
fun HomeScreenUI(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val products = viewModel.getProducts().collectAsLazyPagingItems()
    HomeUI(products)
}

@Composable
fun HomeUI(products: LazyPagingItems<Product>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )
        if (products.loadState.append is LoadState.Loading || products.loadState.refresh is LoadState.Loading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.secondaryContainer,
                strokeCap = StrokeCap.Round
            )
        }

        val state = rememberLazyGridState()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = state,
            contentPadding = PaddingValues(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products.itemCount) {
                ProductCard(product = products[it])
            }
        }
    }
}

@Composable
fun ProductCard(product: Product?) {
    Column(
        modifier = Modifier.background(Color.White, shape = RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = product?.images?.getOrNull(0), contentDescription = "",
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(12.dp))
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = product?.price.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
        Text(
            text = product?.title.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            maxLines = 2,
            lineHeight = 20.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .heightIn(min = 70.dp)
                .padding(start = 10.dp, end = 10.dp)
        )
    }
}