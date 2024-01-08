<script setup lang="ts">
defineProps<{
    cellData?: string
}>()
</script>

<template>
    <td>
        {{ cellData }}
    </td>
</template>

<style>

td {
    background: #eee;
    border: 1px solid;
    font-size: 3vw;
    width: 6vw;
    height: 6vw;
    text-align: center;
}

.light {
    background: #eee;
}

.dark {
    background: #000;
    color: #fff;
}

</style>
