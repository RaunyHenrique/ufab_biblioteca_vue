<template>

  <div>

    <b-modal @hidden="error = false" ref="modalNormal" :id="modal_id" :title="title" :cancel-title="'Cancelar'" :ok-title="'Salvar'">

      <div class="alert alert-danger" v-if="error">
        <ul>
          <li v-for="item in listErros">
            {{item}}
          </li>
        </ul>
      </div>

      <form>

        <div v-for="item in form_fields">

          <!--input: text, number, password e date-->
          <b-form-group v-if="item.type == 'text' || item.type == 'number' || item.type == 'date' || item.type == 'password'"
                        :hidden="item.hidden"
                        :label="item.label"
                        :label-for="item.key">

            <b-form-input :id="item.key"
                          :name="item.key"
                          :type="item.type"
                          v-model="form_modal[item.key]"
                          required
                          :placeholder="item.label">
            </b-form-input>

          </b-form-group>

          <!--select-->
          <b-form-group v-if="item.type == 'select'"
                        :label="item.label"
                        :label-for="item.key">

            <b-form-select :id="item.key"
                           :name="item.key"
                           :options="item.options"
                           required
                           v-model="form_modal[item.key]">
              <template slot="first">
                <option :value="null">Selecione...</option>
              </template>
            </b-form-select>

          </b-form-group>

        </div>

        <slot name="campos_personalizados"></slot>

      </form>

      <div style="text-align: right;" slot="modal-footer" class="w-100">

        <b-btn variant="primary" @click="cancel">
          Cancelar
        </b-btn>

        <b-btn type="submit" variant="primary" @click="ok">
          Salvar
        </b-btn>

      </div>

    </b-modal>

  </div>

</template>

<script>

  export default {
    name: "FormModal",
    props: {
      title: String,
      form_fields: Array,
      form: Object,
      modal_id: String,
      error: Boolean,
      listErros: Array
    },
    data () {
      return {
        form_modal: {}
      }
    },
    updated: function() {
      this.form_modal = this.form
    },
    methods: {
      ok () {
        console.log('okkk')
        this.$emit('ok_modal', this.form_modal)
      },
      cancel () {
        this.$refs.modalNormal.hide()
      },
    }
  }

</script>

<style scoped>

</style>
