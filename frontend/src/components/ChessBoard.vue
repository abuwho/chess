<script setup lang="ts">

import ChessBlock from './ChessBlock.vue';

const rows: number[] = [8, 7, 6, 5, 4, 3, 2, 1];
const cols: string = "abcdefgh"

import { ref } from 'vue';

const lightClass = ref("light")
const darkClass = ref("dark")

</script>


<template>
    <table class="chess-board">
        <tbody>
            <tr>
                <th></th>
                <th v-for="col in cols" :key="col">{{ col }}</th>
            </tr>
            <tr v-for="row in rows" :key="row">
                <th>{{ row }}</th>
                <ChessBlock v-for="(col, colIndex) in cols" :key="col" :cellData="row+col" :class="[(row + colIndex) % 2 === 0 ? darkClass : lightClass]"></ChessBlock>
            </tr>
        </tbody>
    </table>

</template>

<style scoped>
.chess-board {
    display: flex;
    justify-content: center;
    align-items: center;
    border-spacing: 0; 
    border-collapse: collapse;
}

.chess-board th {
    font-size: 3vw;
}



</style>