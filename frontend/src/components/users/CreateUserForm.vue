<script>

import axios from 'axios'
import emitter from 'tiny-emitter/instance';

export default {
  data() {
    return {
      inputValue: '',
      inputDate: undefined,
      validation: null,
      inputRules: [value => value !== '' || 'Should not be empty'],
      dateRules: [value => !!value || 'Should be date'],
    }
  },
  methods: {
    validate() {
      this.$refs.form.validate();
      if (this.validation) {
        axios.post('http://localhost:8080/users/', {
          name: this.inputValue,
          birthDate: this.inputDate
        }).then((response) => {
          emitter.emit('create-user-event', response.data);
        });
      }
    }
  }
}
</script>
<template>
  <va-form style="width: 300px; text-align: center;" ref="form" @validation="validation = $event">
    <va-input class="mb-4" label="Name" v-model="inputValue" :rules="inputRules" />
    <va-date-input label="Date" v-model="inputDate" :rules="dateRules" />
    <va-button class="mb-4 mr-4" style="margin-right: 0 !important; margin-top: 1.5rem;" @click="validate()">
      Create user
    </va-button>
  </va-form>
</template>
<style>
</style>