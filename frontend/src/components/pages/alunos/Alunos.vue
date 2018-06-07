<template>

  <div>

    <!--Title-->
    <b-row>

      <b-col>

        <h1 id="title-page" class="text-left">Alunos</h1>
        <b-button @click.stop="info('Cadastrar', null, null, $event.target)" id="btn-cadastrar" variant="primary" style="display: inline-block;">Cadastrar aluno</b-button>

      </b-col>

    </b-row>

    <hr>

    <!-- User Interface controls -->
    <b-row id="ui-table">

      <b-col md="6" class="my-1" id="per-page-select">
        <b-form-group horizontal label="Por página" class="mb-0">
          <b-form-select :options="pageOptions" v-model="perPage" />
        </b-form-group>
      </b-col>

      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Filtrar" class="mb-0">
          <b-input-group>
            <b-form-input v-model="filter" placeholder="Buscar..." />
            <b-input-group-append>
              <b-btn variant="primary" :disabled="!filter" @click="filter = ''">Apagar</b-btn>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>

    </b-row>

    <!-- Main table element -->
    <b-table show-empty
             stacked="md"
             :items="items"
             :fields="fields"
             :current-page="currentPage"
             :per-page="perPage"
             :filter="filter"
             :sort-by.sync="sortBy"
             :sort-desc.sync="sortDesc"
             :sort-direction="sortDirection"
             @filtered="onFiltered"
             id="table-listar"
    >
      <template slot="name" slot-scope="row">{{row.value}}</template>

      <template slot="curso" slot-scope="row">{{row.value.nome}} - {{row.value.nivel}}</template>

      <template slot="ano_ingresso" slot-scope="row">{{row.value.ano}}.{{row.value.periodo}}</template>

      <template slot="actions" slot-scope="row">
        <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
        <b-button variant="warning" size="sm" @click.stop="info('Editar', row.item, row.index, $event.target)" class="mr-1">
          <strong>Editar</strong>
        </b-button>

        <b-button variant="danger" size="sm" @click.stop="row.toggleDetails">
          <strong>Deletar</strong>
        </b-button>

      </template>

    </b-table>

    <!-- Pagination -->
    <b-row>
      <b-col md="6" class="my-1">
        <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" class="my-0" />
      </b-col>
    </b-row>

    <!-- Info modal -->
    <b-modal id="modalInfo" @hide="resetModal" :title="modalInfo.title" @ok="handleOk">

      <form @submit.stop.prevent="handleSubmit">
        <b-form-input type="text"
                      placeholder="Enter your name"
                      v-model="name"></b-form-input>
      </form>

    </b-modal>

  </div>

</template>

<script>

  const items = [
    { matricula: "154545", nome: 'Dickerson', curso: {nome: 'dasddasd', nivel: 'G'}, ano_ingresso: {ano: '2018', periodo: '1'}, telefone: '45564545',
      cpf: '45564545', rg: '45564545', naturalidade: 'dasdasdd', nomeMae: 'asdasdasd', endereco: 'asdasdasd' },
    { matricula: "458444", nome: 'Larsen', curso: {nome: 'dasddasd', nivel: 'G'}, ano_ingresso: {ano: '2018', periodo: '2'}, telefone: '441144' },
  ]

  export default {
    name: "Alunos",
    data () {
      return {
        items: items,
        fields: [
          { key: 'matricula', label: 'Matricula', sortable: true},
          { key: 'nome', label: 'Nome', sortable: true, 'class': 'text-center' },
          { key: 'curso', label: 'Curso/Graduação', sortable: true },
          { key: 'ano_ingresso', label: 'Ano/Periodo de ingresso', sortable: true },
          { key: 'telefone', label: 'Telefone'},
          { key: 'actions', label: 'Ações' }
        ],
        currentPage: 1,
        perPage: 5,
        totalRows: items.length,
        pageOptions: [ 5, 10, 15 ],
        sortBy: null,
        sortDesc: false,
        sortDirection: 'asc',
        filter: null,
        modalInfo: { title: '', content: '' },
        form: {
          nome: '',//.. etc
        }//colocar campos do form
      }
    },
    methods: {
      info (title, item, index, event) {
        this.modalInfo.title = title
        this.modalInfo.content = item
        this.$root.$emit('bv::show::modal', 'modalInfo', event)
      },
      resetModal () {
        this.form = {}
      },
      onFiltered (filteredItems) {
        // Trigger pagination to update the number of buttons/pages due to filtering
        this.totalRows = filteredItems.length
        this.currentPage = 1
      },
      handleOk (evt) {
        // Prevent modal from closing
        evt.preventDefault()
        if (!this.name) {
          alert('Please enter your name')
        } else {
          this.handleSubmit()
        }
      },
      handleSubmit () {
        this.$refs.modal.hide()
      }
    }
  }

</script>

<style scoped>

  #table-listar {
    margin-top: 16px;
  }
  #per-page-select {
    text-align: left !important;
  }
  .custom-select {
    width: 30% !important;
  }
  #ui-table {
    margin-top: 16px;
  }
  #title-page {
    float: left;
  }
  #btn-cadastrar {
    float: right;
    margin-bottom: 12px;
  }
</style>
